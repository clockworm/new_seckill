package com.bioodas.seckill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bioodas.seckill.dao.UserDao;
import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public User findById(String id) {
		return userDao.findById(id);
	}

	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}

}
