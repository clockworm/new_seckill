package com.bioodas.seckill.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bioodas.seckill.base.BaseTest;
import com.bioodas.seckill.entity.Product;
import com.bioodas.seckill.vo.ProductVO;

public class ProductServiceTest extends BaseTest{

	@Autowired
	ProductService productService; 
	
	@Test
	public void testList() {
		List<ProductVO> list = productService.list();
		System.err.println(list.get(0).getProductName());
	}
	
	@Test
	public void testFindById() {
		Product product = productService.findById("1");
		System.err.println(product);
	}

}
