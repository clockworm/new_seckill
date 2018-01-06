package com.bioodas.seckill.web.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bioodas.seckill.service.LoginService;
import com.bioodas.seckill.util.ResultVOUtil;
import com.bioodas.seckill.vo.ResultVO;
import com.bioodas.seckill.web.form.UserForm;

@Controller
@RequestMapping("common")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping("login")
	public String index() {
		return "common/login";
	}

	@PostMapping("login")
	@ResponseBody
	public ResultVO<?> login(HttpServletResponse response,@Valid UserForm userForm) {
		 boolean login = loginService.login(response,userForm);
		 return ResultVOUtil.success(login);
	}
}
