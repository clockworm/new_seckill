package com.bioodas.seckill.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.service.UserService;
import com.bioodas.seckill.util.MD5Util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author TangLingYun
 * @describe 用户控制器
 * @date 2018年1月15日
 */
@Controller
@RequestMapping("user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("updatePass")
	public String updatePass(User user,Model mode,String newPass) {
		User newUser = new User();
		newUser.setId(user.getId());
		newUser.setMobile(user.getMobile());
		newUser.setPassword(MD5Util.inputPassToDbPass(newPass, user.getSalt()));
		int i = userService.saveOrUpdate(newUser);
		log.info("修改密码是否成功:{}",i==1 ? "成功" : "失败");
		mode.addAttribute("msg", i);
		return "common/fail";
	}
}
