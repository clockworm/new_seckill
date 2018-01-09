package com.bioodas.seckill.entity;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SeckillOrder {
	private String id;
	private String userId;
	private String orderId;
	private String productId;
	private Date createTime;
	private Date updateTime;
}