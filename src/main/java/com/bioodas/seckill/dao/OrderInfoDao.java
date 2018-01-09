package com.bioodas.seckill.dao;

import com.bioodas.seckill.entity.OrderInfo;

public interface OrderInfoDao {
	int deleteByPrimaryKey(String id);

	int insert(OrderInfo record);

	int insertSelective(OrderInfo record);

	OrderInfo selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(OrderInfo record);

	int updateByPrimaryKey(OrderInfo record);
}