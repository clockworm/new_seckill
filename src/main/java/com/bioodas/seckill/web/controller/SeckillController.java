package com.bioodas.seckill.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.enums.ResultEnum;
import com.bioodas.seckill.service.OrderService;
import com.bioodas.seckill.service.ProductService;
import com.bioodas.seckill.vo.ProductVO;

@Controller
@RequestMapping("seckill")
public class SeckillController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("kill/{productId}")
	public String kill(User user,@PathVariable("productId") String productId,Map<String,Object> map) {
		//登录
		//库存
		ProductVO product = productService.findById(productId);
		Integer stock = product.getSeckillStock();
		if(stock <= 0) {
			map.put("msg", ResultEnum.SECKILL_FAIL_NOT_STOCK.getMessage());
			return "seckill/fail"; 
		}
		//判断是否多次秒杀
//		orderService.findSeckillOrderByUserIdAndProductId(user.getId(),productId);
		return null;
	}

}
