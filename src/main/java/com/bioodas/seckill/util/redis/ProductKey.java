package com.bioodas.seckill.util.redis;

public class ProductKey extends BasePrefix{

	public ProductKey(int expireSeconds, String keyPrefix) {
		super(expireSeconds, keyPrefix);
	}
	
	public final static ProductKey generateKeyByList = new ProductKey(60,"list");
	
	public final static ProductKey generateKeyByDetailAndProductId = new ProductKey(60,"detail_%s");

}
