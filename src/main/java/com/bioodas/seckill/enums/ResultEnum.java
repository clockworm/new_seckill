package com.bioodas.seckill.enums;

import lombok.Getter;

@Getter
public enum ResultEnum implements CodeEnum {

	SUCCESS(0, "成功"), 
	PARAM_ERROR(1, "參數不正確"),
	LOGIN_FAIL(2, "密码或用户名不正确"),
	USER_NOT_REGISTER(2, "用户未注册"),
	SECKILL_FAIL_NOT_STOCK(500500, "商品库存不足"),
	SECKILL_REPEATE(500501, "重复秒杀"),
	ERROR(777,"未知错误"),
	SERVER_ERROR(411,"服务端异常,请稍后重试");
	
	/** 返回编码*/
	private Integer code;
	/** 返回信息*/
	private String message;

	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
