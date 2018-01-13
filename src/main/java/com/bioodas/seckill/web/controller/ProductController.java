package com.bioodas.seckill.web.controller;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.service.ProductService;
import com.bioodas.seckill.vo.ProductVO;

/**
 * @author TangLingYun
 * @describe 商品控制器
 * @date 2018年1月6日
 */
@Controller
@RequestMapping("product")
public class ProductController {

	@Autowired
	private ProductService productService; 
	
	@GetMapping("list")
	public String list(User user,Model model) {
		List<ProductVO> list = productService.list();
		model.addAttribute("products", list);
		return "product/list";
	}
	
	@GetMapping("detail/{productId}")
	public String detail(User user,Model model,@PathVariable("productId") String productId) {
		int second = 0;
		ProductVO product = productService.findById(productId);
		model.addAttribute("product", product);
		model.addAttribute("user", user);
		DateTime startTime = new DateTime(product.getStartTime()); 
		DateTime endTime = new DateTime(product.getEndTime()); 
		if(startTime.isAfterNow()) {
			Seconds seconds = Seconds.secondsBetween(startTime, DateTime.now());
			second = Math.abs(seconds.getSeconds());
		}else if(endTime.isBeforeNow()) {
			second = -1;
		}else {
			second = 0;
		}
		model.addAttribute("second", second);
		return "product/detail";
	}
	

}
