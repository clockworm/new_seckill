package com.bioodas.seckill.enums;

import lombok.Getter;

@Getter
public enum ResultEnum implements CodeEnum {

	SUCCESS(0, "成功"), 
	PARAM_ERROR(1, "參數不正確");
	
	/** 返回编码*/
	private Integer code;
	/** 返回信息*/
	private String message;

	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
