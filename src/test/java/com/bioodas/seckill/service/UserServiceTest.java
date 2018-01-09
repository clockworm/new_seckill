package com.bioodas.seckill.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bioodas.seckill.base.BaseTest;
import com.bioodas.seckill.entity.User;

public class UserServiceTest extends BaseTest{
	
	@Autowired
	UserService userService;

	@Test
	public void testFindById() {
		User user = userService.findById("123");
		System.err.println(user);
	}


}
