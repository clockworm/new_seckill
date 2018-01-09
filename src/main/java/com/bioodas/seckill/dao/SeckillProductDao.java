package com.bioodas.seckill.dao;

import com.bioodas.seckill.entity.SeckillProduct;

public interface SeckillProductDao {
    int deleteByPrimaryKey(String id);

    int insert(SeckillProduct record);

    int insertSelective(SeckillProduct record);

    SeckillProduct selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SeckillProduct record);

    int updateByPrimaryKey(SeckillProduct record);
}