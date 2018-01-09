package com.bioodas.seckill.util.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bioodas.seckill.base.BaseTest;
import com.bioodas.seckill.entity.User;

public class RedisClientTest extends BaseTest{
	
	@Autowired
	private RedisClient redisClient;

	@Test
	public void testGet() {
		User user = redisClient.get(UserKey.generateKeyByName,"user1", User.class);
		System.err.println(user);
	}

	@Test
	public void testSet() {
//		User user = new User(KeyUtil.genUniqueKey(), "唐岭云TangLingYun");
//		boolean b = redisClient.set(UserKey.generateKeyByName,"user2", user);
	}
	
	@Test
	public void testDecr() {
		long b = redisClient.incr(UserKey.generateKeyByName,"user2");
		System.err.println(b);
	}

}
