package com.bioodas.seckill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bioodas.seckill.dao.SeckillOrderDao;
import com.bioodas.seckill.entity.OrderInfo;
import com.bioodas.seckill.entity.SeckillOrder;
import com.bioodas.seckill.entity.User;
import com.bioodas.seckill.service.OrderService;
import com.bioodas.seckill.service.SeckillOrderService;
import com.bioodas.seckill.service.SeckillProductService;
import com.bioodas.seckill.util.JsonUtil;
import com.bioodas.seckill.util.KeyUtil;
import com.bioodas.seckill.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SeckillOrderServiceImpl implements SeckillOrderService{
	
	@Autowired
	private SeckillOrderDao seckillOrderDao;
	
	@Autowired
	private SeckillProductService seckillProductService;
	
	@Autowired
	private OrderService orderService;

	@Override
	public SeckillOrder saveOrUpdate(SeckillOrder t) {
		return null;
	}

	@Override
	public SeckillOrder delete(String id) {
		return null;
	}

	@Override
	public SeckillOrder findById(String id) {
		return null;
	}

	@Override
	public List<SeckillOrder> findAll() {
		return null;
	}

	@Override
	public Page<SeckillOrder> findByPage(Pageable page) {
		return null;
	}

	@Override
	public List<SeckillOrder> findSeckillOrderByUserIdAndProductId(String id, String productId) {
		return seckillOrderDao.findSeckillOrderByUserIdAndProductId(id,productId);
	}
	
	@Override
	@Transactional
	public SeckillOrder createSeckillOrder(OrderInfo orderInfo) {
		SeckillOrder seckillOrder = new SeckillOrder();
		seckillOrder.setId(KeyUtil.genUniqueNumKey());
		seckillOrder.setOrderId(orderInfo.getId());
		seckillOrder.setProductId(orderInfo.getProductId());
		seckillOrder.setUserId(orderInfo.getUserId());
		seckillOrderDao.insert(seckillOrder);
		return seckillOrder;
	}


	@Override
	@Transactional
	public OrderInfo seckillProduct(User user, ProductVO productVO) {
		int stock = seckillProductService.reduceStock(productVO);
		log.info("[业务]用户:{}秒杀商品:{}库存减1:{}",user.getId(),productVO.getId(),stock == 1 ? "成功" :"失败");
		OrderInfo orderInfo = orderService.createOrder(user,productVO);
		SeckillOrder seckillOrder = this.createSeckillOrder(orderInfo);
		log.info("[业务]用户:{}秒杀商品:{} 订单出参:{} 秒杀订单出参:{}",user.getId(),productVO.getId(),JsonUtil.toPrintJson(orderInfo),JsonUtil.toPrintJson(seckillOrder));
		return orderInfo;
	}

}
