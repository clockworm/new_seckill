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
		userService.findById("123");
	}

	@Test
	public void testInsert() {
		userService.insert(new User("7891", "TangLingYun31"));
	}

}
