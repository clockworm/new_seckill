package com.bioodas.seckill.aspect;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bioodas.seckill.constant.CookieConstant;
import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.exception.AuthorizeException;
import com.bioodas.seckill.util.CookieUtil;
import com.bioodas.seckill.util.redis.RedisClient;
import com.bioodas.seckill.util.redis.TokenKey;

import lombok.extern.slf4j.Slf4j;

/**
 * @author TangLingYun
 * @describe AOP是否登录效验
 * @date 2018年1月4日
 */
@Aspect
@Component
@Slf4j
public class AuthorizeAspect {

	@Autowired
	private RedisClient redisClient;

	@Pointcut("execution(public  * com.bioodas.seckill.web.controller.*.*(..))" + "&& !execution(public  *  com.bioodas.seckill.web.controller.LoginController.*(..))" + "&& !args(com.bioodas.seckill.entity.User,..)")
	public void verify() {
	}

	@Before("verify()")
	public void doVerify() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		//查询Cookie
		Cookie cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN_KEY);
		if(cookie==null){
			log.warn("[登录效验] 服务端Cookie未找到token");
			throw new AuthorizeException();
		}
		User user = redisClient.get(TokenKey.generateKeyByToken,cookie.getValue(),User.class);
		if(user == null){
			log.warn("[登录效验] Redis端未找到token");
			throw new AuthorizeException();
		}
	}
}
