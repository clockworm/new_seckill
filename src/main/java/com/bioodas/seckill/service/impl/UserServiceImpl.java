package com.bioodas.seckill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bioodas.seckill.dao.UserDao;
import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao UserDao;
	
	@Override
	public User findById(String id) {
		return UserDao.findById(id);
	}

	@Override
	public User login(String mobile, String password) {
		return UserDao.findByMobileAndPassWord(mobile,password);
	}

	@Override
	public User findByMobile(String mobile) {
		return UserDao.findByMobile(mobile);
	}

	@Override
	public User saveOrUpdate(User user) {
		return user;
	}

	@Override
	public User delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findByPage(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
