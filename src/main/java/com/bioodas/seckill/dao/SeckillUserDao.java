package com.bioodas.seckill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.bioodas.seckill.entity.SeckillUser;

@Mapper
public interface SeckillUserDao {
	
	@Select("select * from seckill_user where id =#{id}")
	SeckillUser findById(@Param("id") String id);

	@Select("select * from seckill_user where mobile =#{mobile} and password = #{password}")
	SeckillUser findByMobileAndPassWord(@Param("mobile") String mobile, @Param("password") String password);

	@Select("select * from seckill_user where mobile =#{mobile}")
	SeckillUser findByMobile(@Param("mobile") String mobile);
}
