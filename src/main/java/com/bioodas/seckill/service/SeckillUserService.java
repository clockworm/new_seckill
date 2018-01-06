package com.bioodas.seckill.service;

import com.bioodas.seckill.entity.SeckillUser;

public interface SeckillUserService extends BaseService<SeckillUser> {
	
	SeckillUser findById(String id);

	SeckillUser login(String mobile, String password);

	SeckillUser findByMobile(String mobile);

}
