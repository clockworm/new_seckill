package com.bioodas.seckill.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.bioodas.seckill.entity.Product;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ProductVO extends Product{
	private String seckillProductId;
	private BigDecimal seckillPrice;
	private Integer seckillStock;
	private Date startTime;
	private Date endTime;
}
