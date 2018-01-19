package com.bioodas.seckill.vo;

import com.bioodas.seckill.entity.User;

import groovy.transform.ToString;
import lombok.Data;

@Data
@ToString
public class ProductDetailVO {
	private ProductVO product ;
	private  int seconds;
	private User user;
}
