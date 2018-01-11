package com.bioodas.seckill.service;

import com.bioodas.seckill.entity.OrderInfo;

public interface OrderService extends BaseService<OrderInfo>{

	OrderInfo findSeckillOrderByUserIdAndProductId(String id, String productId);

	
}
