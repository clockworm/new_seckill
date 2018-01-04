package com.bioodas.seckill.aspect;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bioodas.seckill.constant.CookieConstant;
import com.bioodas.seckill.constant.RedisConstant;
import com.bioodas.seckill.exception.AuthorizeException;
import com.bioodas.seckill.util.CookieUtil;

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
	private StringRedisTemplate stringRedisTemplate;

	@Pointcut("execution(public  * com.bioodas.seckill.web.controller.Login*.*(..))" + "&& !execution(public  *  com.bioodas.seckill.web.controller.LoginController.*(..))")
	public void verify() {
	}

	@Before("verify()")
	public void doVerify() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		//查询Cookie
		Cookie cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN_KEY);
		if(cookie==null){
			log.warn("[登录效验] Cookie未找到token");
			throw new AuthorizeException();
		}
		String token = stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
		if(StringUtils.isBlank(token)){
			log.warn("[登录效验] Redis未找到token");
			throw new AuthorizeException();
		}
	}
}
