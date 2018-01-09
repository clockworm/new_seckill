package com.bioodas.seckill.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String list(Model model,User user) {
		List<ProductVO> list = productService.list();
		model.addAttribute("products", list);
		return "product/list";
	}
	

}
