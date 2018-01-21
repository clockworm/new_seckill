package com.bioodas.seckill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.bioodas.seckill.entity.SeckillProduct;

@Mapper
public interface SeckillProductDao {
    int deleteByPrimaryKey(String id);

    int insert(SeckillProduct record);

    int insertSelective(SeckillProduct record);

    SeckillProduct selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SeckillProduct record);

    int updateByPrimaryKey(SeckillProduct record);
    
    @Update("update seckill_product set seckill_stock = seckill_stock -1 where product_id = #{productId} and seckill_stock > 0")
	int reduceStock(SeckillProduct seckillProduct);

    @Select("select * from seckill_product")
	List<SeckillProduct> findAll();
}