package com.bioodas.seckill.dao;

import com.bioodas.seckill.entity.SeckillOrder;

public interface SeckillOrderDao {
    int deleteByPrimaryKey(String id);

    int insert(SeckillOrder record);

    int insertSelective(SeckillOrder record);

    SeckillOrder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SeckillOrder record);

    int updateByPrimaryKey(SeckillOrder record);
}