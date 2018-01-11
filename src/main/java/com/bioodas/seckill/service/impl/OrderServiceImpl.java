package com.bioodas.seckill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bioodas.seckill.dao.OrderInfoDao;
import com.bioodas.seckill.entity.OrderInfo;
import com.bioodas.seckill.service.OrderService;

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
	public OrderInfo findSeckillOrderByUserIdAndProductId(String id, String productId) {
		return null;
	}

}
