package com.bioodas.seckill.rabbitmq;

import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.bioodas.seckill.entity.SeckillOrder;
import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.service.ProductService;
import com.bioodas.seckill.service.SeckillOrderService;
import com.bioodas.seckill.util.JsonUtil;
import com.bioodas.seckill.util.redis.ProductKey;
import com.bioodas.seckill.util.redis.RedisClient;
import com.bioodas.seckill.vo.ProductVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RabbitMQRecver {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SeckillOrderService seckillOrderService;
	
	@Autowired
	private RedisClient redisCilent;
	
	@RabbitListener(queues = "user.login.queue")
	public void userLoginQueueListener(@Payload String message,CorrelationData correlationData) throws Exception{
		log.info("该用户完成了登录:{}",message);
	}
	
	@RabbitListener(queues = "headers.queue")
	public void headersQueueListener(@Payload byte[] message) throws Exception{
		log.info("header:{}",new String(message));
	}
	
	@RabbitListener(queues = "product.kill.queue")
	public void productKillQueueListener(@Payload String message) throws Exception{
		log.info("开始秒杀订单处理:{}",message);
		Map<?,?> map = JsonUtil.jsonToObject(message, Map.class);
		String userStr = (String)map.get("user");
		User user = JsonUtil.jsonToObject(userStr, User.class);
		String productId = (String) map.get("productId");
		System.err.println(userStr);
		System.err.println(productId);
		
		//库存
		ProductVO product = productService.findById(productId);
		Integer stock = product.getSeckillStock();
		if(stock <= 0) {
			log.info("秒杀商品存不足:{}",message);
			redisCilent.set(ProductKey.generateKeyByProductNoNeStock, productId, productId);
			return;
		}
		
		//判断秒杀订单是否多次秒杀
		List<SeckillOrder> list = seckillOrderService.findSeckillOrderByUserIdAndProductId(user.getId(),productId);
		if(!CollectionUtils.isEmpty(list)) {
			log.info("重复秒杀:{}",message);
			return;
		} 
		
		log.info("[请求]用户:{}开始秒杀商品:{}当前库存:{}",user.getId(),product.getId(),stock);
		//开始秒杀 减库存 下订单 写入秒杀订单
		seckillOrderService.seckillProduct(user,product);
		log.info("处理秒杀订单结束:{}",message);
	}
	
}
