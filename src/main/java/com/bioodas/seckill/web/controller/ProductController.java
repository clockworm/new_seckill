package com.bioodas.seckill.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.service.ProductService;
import com.bioodas.seckill.util.ResultVOUtil;
import com.bioodas.seckill.util.redis.ProductKey;
import com.bioodas.seckill.util.redis.RedisClient;
import com.bioodas.seckill.vo.ProductDetailVO;
import com.bioodas.seckill.vo.ProductVO;
import com.bioodas.seckill.vo.ResultVO;

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
	
	@Autowired
	private RedisClient redisClient;
	
	@Autowired
	private ThymeleafViewResolver thymeleafViewResolver;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@GetMapping(value="list",produces="text/html")
	public @ResponseBody String list(User user,HttpServletRequest request,HttpServletResponse response,Model model) {
		//缓存取出页面
		String html = redisClient.get(ProductKey.generateKeyByList, "list", String.class);
		if(StringUtils.isNotBlank(html)) return html;
		List<ProductVO> list = productService.list();
		model.addAttribute("products", list);
		//页面放入缓存
		SpringWebContext context = new SpringWebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap(), applicationContext);
		html = thymeleafViewResolver.getTemplateEngine().process("product/list", context);
		redisClient.set(ProductKey.generateKeyByList, "list", html);
		return html;
	}
	
	/**Redis页面缓存*/
	@GetMapping(value="detail/{productId}",produces="text/html")
	public @ResponseBody String detail(User user,HttpServletRequest request,HttpServletResponse response,Model model,@PathVariable("productId") String productId) {
		//缓存取出页面
		String html = redisClient.get(ProductKey.generateKeyByDetailAndProductId, productId, String.class);
		if(StringUtils.isNotBlank(html)) return html;
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
		//页面放入缓存
		SpringWebContext context = new SpringWebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap(), applicationContext);
		html = thymeleafViewResolver.getTemplateEngine().process("product/detail", context);
		redisClient.set(ProductKey.generateKeyByDetailAndProductId, productId, html);
		return html;
	}
	

	@GetMapping(value="detail/{productId}")
	public @ResponseBody ResultVO<?> detailStatic(User user,HttpServletRequest request,HttpServletResponse response,Model model,@PathVariable("productId") String productId) {
		int second = 0;
		ProductVO product = productService.findById(productId);
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
		ProductDetailVO productDetailVO = new ProductDetailVO();
		productDetailVO.setSeconds(second);
		productDetailVO.setProduct(product);
		productDetailVO.setUser(user);
		return ResultVOUtil.success(productDetailVO);
	}
	
}
