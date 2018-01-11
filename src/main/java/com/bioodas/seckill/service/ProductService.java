package com.bioodas.seckill.service;

import java.util.List;

import com.bioodas.seckill.entity.Product;
import com.bioodas.seckill.vo.ProductVO;

public interface ProductService extends BaseService<Product>{
	
	  public List<ProductVO> list(); 
	  
	  public ProductVO findById(String id); 
	
}
