package com.bioodas.seckill.enums;

import lombok.Getter;

/**
 * @author TangLingYun
 * @describe 下单来源
 * @date 2018年1月13日
 */
@Getter
public enum OrderChannelEnum implements CodeEnum {

	MOBILE(0, "移动端"), 
	WEB(1, "网页端");
	
	/** 返回编码 */
	private Integer code;
	/** 返回信息 */
	private String message;

	OrderChannelEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
