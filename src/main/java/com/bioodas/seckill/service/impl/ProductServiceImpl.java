package com.bioodas.seckill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bioodas.seckill.dao.ProductDao;
import com.bioodas.seckill.entity.Product;
import com.bioodas.seckill.service.ProductService;
import com.bioodas.seckill.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {


	@Autowired
	private ProductDao productDao;

	@Override
	public List<ProductVO> list() {
		return productDao.list();
	}

	@Override
	public int saveOrUpdate(Product t) {
		return 0;
	}

	@Override
	public Product delete(String id) {
		return null;
	}


	@Override
	public List<Product> findAll() {
		return null;
	}

	@Override
	public Page<Product> findByPage(Pageable page) {
		return null;
	}

	@Override
	public ProductVO findById(String id) {
		return productDao.findById(id);
	}

	
}
