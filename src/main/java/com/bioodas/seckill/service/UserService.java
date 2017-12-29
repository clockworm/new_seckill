package com.bioodas.seckill.service;

import com.bioodas.seckill.entity.User;

public interface UserService {
	
	User findById(String id);
	
	int insert(User user);

}
