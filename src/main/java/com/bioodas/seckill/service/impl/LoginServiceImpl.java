package com.bioodas.seckill.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bioodas.seckill.entity.SeckillUser;
import com.bioodas.seckill.enums.ResultEnum;
import com.bioodas.seckill.exception.SeckillException;
import com.bioodas.seckill.service.LoginService;
import com.bioodas.seckill.service.SeckillUserService;
import com.bioodas.seckill.util.CookieUtil;
import com.bioodas.seckill.util.JsonUtil;
import com.bioodas.seckill.util.KeyUtil;
import com.bioodas.seckill.util.MD5Util;
import com.bioodas.seckill.util.redis.RedisClient;
import com.bioodas.seckill.util.redis.TokenKey;
import com.bioodas.seckill.web.form.UserForm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService{

	@Autowired
	private SeckillUserService seckillUserService;

	@Autowired
	private RedisClient redisClient;

	@Override
	public boolean login(HttpServletResponse response,UserForm userForm) {
		log.info(userForm.getMobile()+":"+userForm.getPassword());
		/**密码效验*/
		SeckillUser seckillUser = seckillUserService.findByMobile(userForm.getMobile());
		if(seckillUser==null) throw new SeckillException(ResultEnum.USER_NOT_REGISTER);
		log.info(JsonUtil.toPrintJson(seckillUser));
		String calcPass = MD5Util.formPassToDBPass(userForm.getPassword(), seckillUser.getSalt());
		log.info("calcPass:"+calcPass);
		if (!calcPass.equals(seckillUser.getPassword())) {
			throw new SeckillException(ResultEnum.LOGIN_FAIL);
		}
		/**登录成功写入redis*/
		//1 生成token
		String token = KeyUtil.genUniqueID();
		//2 写入redis
		redisClient.set(TokenKey.generateKeyByToken, token, seckillUser);
		//3 写入cook
		CookieUtil.setCookie(response, TokenKey.TOKEN_KEY, token, TokenKey.generateKeyByToken.expireSeconds());
		return true;
	}




	/**退出登录*/
	@Override
	public void logout(HttpServletRequest httpServletRequest, HttpServletResponse response) {
		//查询Cookie
		Cookie cookie = CookieUtil.getCookie(httpServletRequest, TokenKey.TOKEN_KEY);
		if(cookie!=null){
			//清除Redis
			//redisClient.delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
			//清除Cookie
			CookieUtil.setCookie(response,TokenKey.TOKEN_KEY,null,0);
		}
	}
	
	
}
