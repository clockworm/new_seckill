package com.bioodas.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.util.JsonUtil;
import com.bioodas.seckill.util.KeyUtil;
import com.bioodas.seckill.util.ResultVOUtil;
import com.bioodas.seckill.vo.ResultVO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserDaoTest {

	@Autowired
	UserDao userDao;

	@Test
	public void findById() throws Exception {
		User user = userDao.findById("123");
		ResultVO<?> vo = ResultVOUtil.success(user);
		log.info("{}", JsonUtil.toPrintJson(vo));
	}
	
	
	@Test
	public void insert() throws Exception {
		int i = userDao.insert(new User(KeyUtil.genUniqueKey(),"TangXiaoHua"));
		System.err.println(i);
	}

}