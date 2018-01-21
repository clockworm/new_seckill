package com.bioodas.seckill.web.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bioodas.seckill.entity.SeckillOrder;
import com.bioodas.seckill.entity.SeckillProduct;
import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.enums.ResultEnum;
import com.bioodas.seckill.exception.AuthorizeException;
import com.bioodas.seckill.exception.SeckillException;
import com.bioodas.seckill.rabbitmq.RabbitMQSender;
import com.bioodas.seckill.rabbitmq.RabbitRoutingEnum;
import com.bioodas.seckill.service.SeckillOrderService;
import com.bioodas.seckill.service.SeckillProductService;
import com.bioodas.seckill.util.JsonUtil;
import com.bioodas.seckill.util.ResultVOUtil;
import com.bioodas.seckill.util.redis.ProductKey;
import com.bioodas.seckill.util.redis.RedisClient;
import com.bioodas.seckill.vo.ResultVO;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SeckillController implements InitializingBean{
	
	@Autowired
	private SeckillOrderService seckillOrderService;
	
	@Autowired
	private SeckillProductService seckillProductService;
	
	@Autowired
	private RedisClient redisClient;
	
	@Autowired
	private RabbitMQSender sender;
	

	@Override
	public void afterPropertiesSet() throws Exception {
		/**控制器初始化之后 秒杀商品的库存放入Redis*/
		List<SeckillProduct> list = seckillProductService.findAll();
		for (SeckillProduct seckillProduct : list) {
			redisClient.set(ProductKey.generateKeyByProductStock, seckillProduct.getProductId(), seckillProduct.getSeckillStock());
		}
	}
	
	@PostMapping("kill")
	public String kill(User user,String productId,Map<String,Object> map) {
		//登录
		if(user == null) {
			throw new AuthorizeException();
		}
		//预减库存
		long stock = redisClient.decr(ProductKey.generateKeyByProductStock, productId);
		if(stock < 0) throw new SeckillException(ResultEnum.SECKILL_FAIL_NOT_STOCK);
		//预下订单
		ConcurrentMap<String,Object> body = Maps.newConcurrentMap();
		body.put("user", user);
		body.put("productId", productId);
		String message = JsonUtil.obejctToJson(body, false);
		try {
			sender.send(message, RabbitRoutingEnum.PRODUCT_KILL_ROUTING);
		} catch (Exception e) {
			log.error("发送MQ消息异常:{}",e);
			throw new SeckillException(ResultEnum.ERROR);
		}
		map.put("msg", "秒杀处理中");
		return "common/fail";
	}
	
	@GetMapping("seckillResult")
	@ResponseBody
	public ResultVO<?> seckillResult(User user,String productId){
		if(redisClient.exists(ProductKey.generateKeyByProductNoNeStock, productId)) {
			throw new SeckillException(ResultEnum.SECKILL_FAIL_NOT_STOCK);
		}
		List<SeckillOrder> list = seckillOrderService.findSeckillOrderByUserIdAndProductId(user.getId(), productId);
		if(!CollectionUtils.isEmpty(list)) {
			return ResultVOUtil.success(list);
		}
		return ResultVOUtil.success("秒杀处理中");
	}

}
