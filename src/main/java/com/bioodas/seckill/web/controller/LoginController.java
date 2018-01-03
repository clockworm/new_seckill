package com.bioodas.seckill.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bioodas.seckill.entity.SeckillUser;
import com.bioodas.seckill.service.SeckillUserService;
import com.bioodas.seckill.util.MD5Util;
import com.bioodas.seckill.util.ResultVOUtil;
import com.bioodas.seckill.vo.ResultVO;
import com.bioodas.seckill.web.form.UserForm;

@Controller
@RequestMapping("user")
public class LoginController {

	@Autowired
	private SeckillUserService seckillUserService;

	@GetMapping("login")
	public String index() {
		return "user/login";
	}

	@PostMapping("login")
	@ResponseBody
	public ResultVO<?> login(@Valid UserForm userForm) {
		System.err.println(userForm.getMobile()+":"+userForm.getPassword());
		/**密码效验*/
		SeckillUser seckillUser = seckillUserService.findByMobile(userForm.getMobile());
		System.err.println(seckillUser);
		String calcPass = MD5Util.formPassToDBPass(userForm.getPassword(), seckillUser.getSalt());
		System.err.println("calcPass:"+calcPass);
		if(calcPass.equals(seckillUser.getPassword())) {
			return	ResultVOUtil.success(seckillUser);
		}
		return ResultVOUtil.fail("登录失败");
	}
}
