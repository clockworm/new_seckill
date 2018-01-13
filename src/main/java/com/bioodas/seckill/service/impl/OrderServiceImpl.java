package com.bioodas.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bioodas.seckill.dao.OrderInfoDao;
import com.bioodas.seckill.entity.OrderInfo;
import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.enums.OrderChannelEnum;
import com.bioodas.seckill.enums.OrderStatusEnum;
import com.bioodas.seckill.service.OrderService;
import com.bioodas.seckill.util.KeyUtil;
import com.bioodas.seckill.vo.ProductVO;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderInfoDao orderInfoDao;

	@Override
	public OrderInfo saveOrUpdate(OrderInfo t) {
		return null;
	}

	@Override
	public OrderInfo delete(String id) {
		return null;
	}

	@Override
	public OrderInfo findById(String id) {
		return orderInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public List<OrderInfo> findAll() {
		return null;
	}

	@Override
	public Page<OrderInfo> findByPage(Pageable page) {
		return null;
	}

	@Override
	public OrderInfo findOrderByUserIdAndProductId(String userId, String productId) {
		return null;
	}

	@Override
	@Transactional
	public OrderInfo createOrder(User user, ProductVO product) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setId(KeyUtil.genUniqueNumKey());
		orderInfo.setCreateTime(new Date());
		orderInfo.setDeliveryAddrId(KeyUtil.genUniqueID());
		orderInfo.setProductQuantity(1);
		orderInfo.setProductId(product.getId());
		orderInfo.setProductName(product.getProductName());
		orderInfo.setProductPrice(product.getSeckillPrice());
		orderInfo.setStatus(OrderStatusEnum.CREATE_ORDER_NOT_PAY.getCode());
		orderInfo.setOrderChannel(OrderChannelEnum.MOBILE.getCode());
		orderInfo.setUserId(user.getId());
		orderInfo.setCreateTime(new Date());
		orderInfoDao.insert(orderInfo);
		return orderInfo;
	}

}
