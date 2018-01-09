package com.bioodas.seckill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bioodas.seckill.entity.User;

@Mapper
public interface UserDao {
	
	@Select("select * from user where id =#{id}")
	User findById(@Param("id") String id);

	@Select("select * from user where mobile =#{mobile} and password = #{password}")
	User findByMobileAndPassWord(@Param("mobile") String mobile, @Param("password") String password);

	@Select("select * from user where mobile =#{mobile}")
	User findByMobile(@Param("mobile") String mobile);
}
