package com.bioodas.seckill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bioodas.seckill.dao.SeckillProductDao;
import com.bioodas.seckill.entity.SeckillProduct;
import com.bioodas.seckill.service.SeckillProductService;
import com.bioodas.seckill.vo.ProductVO;

@Service
public class SeckillProductServiceImpl implements SeckillProductService{
	
	@Autowired
	private SeckillProductDao seckillProductDao;
	
	@Override
	@Transactional
	public int reduceStock(ProductVO productVO) {
		SeckillProduct procut = new SeckillProduct();
		procut.setProductId(productVO.getId());
		procut.setSeckillStock(productVO.getSeckillStock());
		return seckillProductDao.reduceStock(procut);
	}
}
