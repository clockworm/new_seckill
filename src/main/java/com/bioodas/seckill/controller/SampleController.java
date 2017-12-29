package com.bioodas.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bioodas.seckill.dao.UserDao;
import com.bioodas.seckill.util.ResultVOUtil;

@Controller
@RequestMapping("demo")
public class SampleController {

	@Autowired
	UserDao userDao;
	
	@GetMapping("test")
	@ResponseBody
	public Object thymeleaf() {
		return ResultVOUtil.success(userDao.findById("123"));
	}
}
