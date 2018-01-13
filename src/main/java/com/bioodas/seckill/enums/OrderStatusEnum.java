package com.bioodas.seckill.enums;

import lombok.Getter;

/**
 * @author TangLingYun
 * @describe 订单状态
 * @date 2018年1月13日
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {
	
	CREATE_ORDER_NOT_PAY(0,"创建订单,未支付"),
	ALREADY_PAY_ORDER(1,"已支付,待发货"),
	SEND_OUT(2,"已发货"),
	ALREADY_RECEIVE(3,"已签收"),
	REFUND_ORDER(4,"已退款"),
	OVER_ORDER(5,"完结订单")
	
	;
	
	/** 返回编码*/
	private Integer code;
	/** 返回信息*/
	private String message;

	OrderStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
