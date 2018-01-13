package com.bioodas.seckill.service;

import com.bioodas.seckill.vo.ProductVO;

public interface SeckillProductService {

	/**秒杀的商品库存减1*/
	int reduceStock(ProductVO productVO);

}
