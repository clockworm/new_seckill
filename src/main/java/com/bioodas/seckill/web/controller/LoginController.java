package com.bioodas.seckill.web.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bioodas.seckill.exception.SeckillException;
import com.bioodas.seckill.rabbitmq.RabbitMQSender;
import com.bioodas.seckill.rabbitmq.RabbitRoutingEnum;
import com.bioodas.seckill.service.LoginService;
import com.bioodas.seckill.util.JsonUtil;
import com.bioodas.seckill.util.ResultVOUtil;
import com.bioodas.seckill.vo.ResultVO;
import com.bioodas.seckill.web.form.UserForm;

@Controller
@RequestMapping("common")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private RabbitMQSender sender;

	@GetMapping("login")
	public String index() {
		return "common/login";
	}

	@PostMapping("login")
	@ResponseBody
	public ResultVO<?> login(HttpServletResponse response,@Valid UserForm userForm) {
		 boolean login = loginService.login(response,userForm);
		 if(login){
			 try {
				sender.send(JsonUtil.obejctToJson(userForm, false), RabbitRoutingEnum.USER_LOGIN_ROUTING);
				MessageProperties properties = new MessageProperties();
				properties.setHeader("sendInstruction", true);
				sender.send(JsonUtil.obejctToJson(userForm, false),properties, RabbitRoutingEnum.HEADERS_ROUTING);
			} catch (Exception e) {
				throw new SeckillException(7381,e.getMessage());
			}
		 }
		 return ResultVOUtil.success(login);
	}
}
