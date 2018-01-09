package com.bioodas.seckill.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderInfo {
	private String id;
	private String userId;
	private String productId;
	private String deliveryAddrId;
	private String productName;
	private BigDecimal productPrice;
	private Integer productQuantity;
	private int orderChannel;
	private int status;
	private Date createTime;
	private Date updateTime;
}