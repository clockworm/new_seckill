package com.bioodas.seckill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.bioodas.seckill.entity.SeckillOrder;

@Mapper
public interface SeckillOrderDao {
	
    int deleteByPrimaryKey(String id);

    int insert(SeckillOrder record);

    int insertSelective(SeckillOrder record);

    SeckillOrder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SeckillOrder record);

    int updateByPrimaryKey(SeckillOrder record);

    @Select("select *  from seckill_order where user_id=#{userId} and product_id=#{productId}")
	List<SeckillOrder> findSeckillOrderByUserIdAndProductId(@Param("userId") String userId,@Param("productId") String productId);
}