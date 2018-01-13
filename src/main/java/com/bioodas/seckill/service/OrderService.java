package com.bioodas.seckill.service;

import com.bioodas.seckill.entity.OrderInfo;
import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.vo.ProductVO;

public interface OrderService extends BaseService<OrderInfo>{

	OrderInfo findOrderByUserIdAndProductId(String userId, String productId);

	OrderInfo createOrder(User user, ProductVO product);
	
}
