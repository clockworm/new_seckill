package com.bioodas.seckill.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SeckillProduct {
	private String id;
	private String productId;
	private BigDecimal seckillPrice;
	private Integer seckillStock;
	private Date startTime;
	private Date endTime;
	private Date insertTime;
	private Date lastUpdateTime;
}