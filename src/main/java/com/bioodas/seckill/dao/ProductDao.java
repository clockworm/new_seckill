package com.bioodas.seckill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bioodas.seckill.entity.Product;
import com.bioodas.seckill.vo.ProductVO;

@Mapper
public interface ProductDao {
    int deleteByPrimaryKey(String id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(Product record);

    int updateByPrimaryKey(Product record);
    
    @Select("select p.*,sp.id as seckill_product_id,sp.seckill_price,sp.seckill_stock,sp.start_time,sp.end_time  from seckill_product sp left join product p on sp.product_id = p.id")
    public List<ProductVO> list(); 
    
    @Select("select p.*,sp.id as seckill_product_id,sp.seckill_price,sp.seckill_stock,sp.start_time,sp.end_time  from seckill_product sp left join product p on sp.product_id = p.id where p.id=#{productId}")
    ProductVO findById(@Param("productId") String productId);

}