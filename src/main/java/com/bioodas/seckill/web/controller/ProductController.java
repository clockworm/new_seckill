package com.bioodas.seckill.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bioodas.seckill.entity.SeckillUser;

/**
 * @author TangLingYun
 * @describe 商品控制器
 * @date 2018年1月6日
 */
@Controller
@RequestMapping("product")
public class ProductController {

	@GetMapping("list")
	public String list(Model model,SeckillUser user) {
		model.addAttribute("user", user);
		return "product/list";
	}
	

}
