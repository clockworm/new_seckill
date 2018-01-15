package com.bioodas.seckill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bioodas.seckill.dao.UserDao;
import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public User findById(String id) {
		return userDao.findById(id);
	}

	@Override
	public User login(String mobile, String password) {
		return userDao.findByMobileAndPassWord(mobile,password);
	}

	@Override
	@Cacheable(cacheNames="user",key="#p0")
	public User findByMobile(String mobile) {
		return userDao.findByMobile(mobile);
	}

	@Override
	@CacheEvict(cacheNames="user",key="#user.mobile")
	@Transactional
	public int saveOrUpdate(User user) {
		return  userDao.updateByPrimaryKeySelective(user);
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
