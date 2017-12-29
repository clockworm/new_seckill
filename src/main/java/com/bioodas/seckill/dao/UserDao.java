package com.bioodas.seckill.dao;

import com.bioodas.seckill.entity.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
	@Select("select * from user where id = #{id}")
	User findById(@Param("id") String id);

	@Insert("insert into user(id,name) values(#{id},#{name})")
	int insert(User user);
}
