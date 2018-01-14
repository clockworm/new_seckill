package com.bioodas.seckill.dao;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;

import com.bioodas.seckill.base.BaseTest;
import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.util.JsonUtil;
import com.bioodas.seckill.util.KeyUtil;
import com.bioodas.seckill.util.MD5Util;
import com.bioodas.seckill.util.ResultVOUtil;
import com.bioodas.seckill.util.redis.RedisClient;
import com.bioodas.seckill.vo.ResultVO;
import com.bioodas.seckill.web.form.UserForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDaoTest extends BaseTest{

	@Autowired
	UserDao userDao;
	
	@Autowired
	RedisClient redisClient;
	
	@Autowired  
    RestTemplateBuilder builder;  
	

	@Test
	public void findById() throws Exception {
		User user = userDao.selectByPrimaryKey("111");
		ResultVO<?> vo = ResultVOUtil.success(user);
		log.info("{}", JsonUtil.toPrintJson(vo));
	}
	
	@Test
	public void insert() throws Exception {
		for (int i = 0; i < 5000; i++) {
			User user = new User();
			user.setId(KeyUtil.genUniqueID());
			String str = "1"+(RandomUtils.nextLong(1000000000L,9999999999L));
			String salt = KeyUtil.genUniqueNumKey();
			user.setMobile(str);
			user.setNickname(str);
			user.setPassword(MD5Util.formPassToDBPass(str,salt));
			user.setSalt(salt);
			userDao.insert(user);

			UserForm userForm = new UserForm();
			userForm.setMobile(user.getMobile());
			userForm.setPassword(str);
			builder.build().postForObject("http://127.0.0.1:9000/seckill/common/login", userForm, String.class);
		}
	}
	
	
}
