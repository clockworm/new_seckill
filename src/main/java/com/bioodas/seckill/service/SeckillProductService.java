package com.bioodas.seckill.service;

import java.util.List;

import com.bioodas.seckill.entity.SeckillProduct;
import com.bioodas.seckill.vo.ProductVO;

public interface SeckillProductService {

	/**秒杀的商品库存减1*/
	int reduceStock(ProductVO productVO);
	
	/**查询所有的秒杀商品*/
	List<SeckillProduct> findAll();
	
}
