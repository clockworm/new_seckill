package com.bioodas.seckill.handler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.exception.AuthorizeException;
import com.bioodas.seckill.util.CookieUtil;
import com.bioodas.seckill.util.redis.RedisClient;
import com.bioodas.seckill.util.redis.TokenKey;


/**
 * @author TangLingYun
 * @describe 如果用户登录  控制层第一个参数为User类型的 自动赋值 (参数自构建)
 * @date 2018年1月7日
 */
@Component
public class UserArgumentResolverHandler implements HandlerMethodArgumentResolver {

	@Autowired
	private RedisClient redisClient;

	/** 如果请求controller中带有改参数的  进行调用 {@link #resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) }方法*/
	@Override
	public boolean supportsParameter(MethodParameter arg0) {
		Class<?> clazz = arg0.getParameterType();
		return clazz == User.class;
	}

	/**参数注入 赋值*/
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
		String token = request.getParameter(TokenKey.TOKEN_KEY);
		if (StringUtils.isBlank(token)) {
			Cookie cookie = CookieUtil.getCookie(request, TokenKey.TOKEN_KEY);
			if (cookie == null) throw new AuthorizeException();
			token = cookie.getValue();
		}
		User user = redisClient.get(TokenKey.generateKeyByToken, token, User.class);
		if(user == null) throw new AuthorizeException();
		redisClient.set(TokenKey.generateKeyByToken, token, user);
		CookieUtil.setCookie(response, TokenKey.TOKEN_KEY, token, TokenKey.generateKeyByToken.expireSeconds());
		return user;
	}

}
