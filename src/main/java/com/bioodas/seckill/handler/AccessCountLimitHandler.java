package com.bioodas.seckill.handler;

import java.io.OutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bioodas.seckill.annotation.AccessLimit;
import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.enums.ResultEnum;
import com.bioodas.seckill.exception.AuthorizeException;
import com.bioodas.seckill.util.CookieUtil;
import com.bioodas.seckill.util.JsonUtil;
import com.bioodas.seckill.util.ResultVOUtil;
import com.bioodas.seckill.util.redis.RedisClient;
import com.bioodas.seckill.util.redis.TokenKey;
import com.bioodas.seckill.util.redis.UserKey;

/**
 * @author TangLingYun
 * @detail 接口防刷(用于登录之后的接口)
 */
@Component
public class AccessCountLimitHandler extends HandlerInterceptorAdapter {

	@Autowired
	private RedisClient redisClient;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod)handler;
			AccessLimit limit = hm.getMethodAnnotation(AccessLimit.class);
			if(limit == null) return true;
			String url = request.getRequestURI();
			UserKey userKey = new UserKey(limit.seconds(), "access_limit_"+url+"_%s");
			User user = getUser(request);
			Integer accessCount = redisClient.get(userKey,user.getId(), Integer.class);
			if(accessCount == null  || accessCount == 0){
				redisClient.set(userKey,user.getId(), 1);
			}else if(accessCount < limit.maxCount()){
				redisClient.incr(userKey, user.getId());
			}else{
				response.setContentType("application/json;charset=UTF-8");
				try(OutputStream out = response.getOutputStream()){
					out.write(JsonUtil.obejctToJson(ResultVOUtil.fail(ResultEnum.ACCESS_LIMIT.getMessage()), false).getBytes("UTF-8"));
					out.flush();
				}
				return false;
			}
		}
		return true;
	}

	/**获取用户对象*/
	private User getUser(HttpServletRequest request) {
		String token = request.getParameter(TokenKey.TOKEN_KEY);
		if (StringUtils.isBlank(token)) {
			Cookie cookie = CookieUtil.getCookie(request, TokenKey.TOKEN_KEY);
			if (cookie == null) throw new AuthorizeException();
			token = cookie.getValue();
		}
		return redisClient.get(TokenKey.generateKeyByToken, token, User.class);
	}
}
