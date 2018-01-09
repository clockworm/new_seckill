package com.bioodas.seckill.service;

import com.bioodas.seckill.entity.User;

public interface UserService extends BaseService<User> {
	
	User findById(String id);

	User login(String mobile, String password);

	User findByMobile(String mobile);

}
