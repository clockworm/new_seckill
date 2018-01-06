package com.bioodas.seckill.util.redis;

/**
 * @author TangLingYun
 * @describe 订单redis生成key策略
 * @date 2017年12月30日
 */
public class OrderKey extends BasePrefix{

	public OrderKey(int expireSeconds, String keyPrefix) {
		super(expireSeconds, keyPrefix);
	}

}
