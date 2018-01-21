package com.bioodas.seckill.util.redis;

public class ProductKey extends BasePrefix{

	public ProductKey(int expireSeconds, String keyPrefix) {
		super(expireSeconds, keyPrefix);
	}
	
	public final static ProductKey generateKeyByList = new ProductKey(29,"product_list");
	
	public final static ProductKey generateKeyByDetailAndProductId = new ProductKey(29,"product_detail_%s");
	
	public final static ProductKey generateKeyByProductStock= new ProductKey(60*60*24*30,"product_stock_%s");
	
	public final static ProductKey generateKeyByProductNoNeStock= new ProductKey(60*60*24*30,"product_none_stock_%s");

}
