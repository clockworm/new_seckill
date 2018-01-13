package com.bioodas.seckill.service;

import java.util.List;
import com.bioodas.seckill.entity.OrderInfo;
import com.bioodas.seckill.entity.SeckillOrder;
import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.vo.ProductVO;

public interface SeckillOrderService extends BaseService<SeckillOrder>{

	/**查询秒杀订单*/
	List<SeckillOrder> findSeckillOrderByUserIdAndProductId(String userId, String productId);
	
	/**开始秒杀商品业务*/
	OrderInfo seckillProduct(User user, ProductVO product);
	
	/**创建秒杀订单*/
	SeckillOrder createSeckillOrder(OrderInfo orderInfo);
}
