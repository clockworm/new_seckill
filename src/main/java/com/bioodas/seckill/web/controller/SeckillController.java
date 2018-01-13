package com.bioodas.seckill.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;

import com.bioodas.seckill.entity.OrderInfo;
import com.bioodas.seckill.entity.SeckillOrder;
import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.enums.ResultEnum;
import com.bioodas.seckill.exception.AuthorizeException;
import com.bioodas.seckill.service.ProductService;
import com.bioodas.seckill.service.SeckillOrderService;
import com.bioodas.seckill.vo.ProductVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SeckillController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SeckillOrderService seckillOrderService;
	
	@PostMapping("kill")
	public String kill(User user,String productId,Map<String,Object> map) {
		//登录
		if(user == null) {
			throw new AuthorizeException();
		}
		
		//库存
		ProductVO product = productService.findById(productId);
		Integer stock = product.getSeckillStock();
		if(stock <= 0) {
			map.put("msg", ResultEnum.SECKILL_FAIL_NOT_STOCK.getMessage());
			return "seckill/fail"; 
		}
		//判断秒杀订单是否多次秒杀
		List<SeckillOrder> list = seckillOrderService.findSeckillOrderByUserIdAndProductId(user.getId(),productId);
		if(!CollectionUtils.isEmpty(list)) {
			map.put("msg", ResultEnum.SECKILL_REPEATE.getMessage());
			return "seckill/fail"; 
		}
		log.info("[请求]用户:{}开始秒杀商品:{}当前库存:{}",user.getId(),product.getId(),stock);
		//开始秒杀 减库存 下订单 写入秒杀订单
		OrderInfo orderInfo = seckillOrderService.seckillProduct(user,product);
		map.put("orderInfo", orderInfo);
		map.put("product", product);
		return "order/detail";
	}

}
