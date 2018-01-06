package com.bioodas.seckill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.bioodas.seckill.dao.SeckillUserDao;
import com.bioodas.seckill.entity.SeckillUser;
import com.bioodas.seckill.service.SeckillUserService;

@Service
public class SeckillUserServiceImpl implements SeckillUserService{

	@Autowired
	SeckillUserDao seckillUserDao;
	
	@Override
	public SeckillUser findById(String id) {
		return seckillUserDao.findById(id);
	}

	@Override
	public SeckillUser login(String mobile, String password) {
		return seckillUserDao.findByMobileAndPassWord(mobile,password);
	}

	@Override
	public SeckillUser findByMobile(String mobile) {
		return seckillUserDao.findByMobile(mobile);
	}

	@Override
	public SeckillUser saveOrUpdate(SeckillUser user) {
		return user;
	}

	@Override
	public SeckillUser delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<SeckillUser> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SeckillUser> findByPage(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
