package com.bioodas.seckill.rabbitmq;

import lombok.Getter;

/**
 * @author TangLingYun
 * @detail MQ路由配置枚举
 * @use 路由枚举(队列名称,交互)
 */
@Getter
public enum RabbitRoutingEnum {

	/*用户登录MQ路由枚举*/
	USER_LOGIN_ROUTING("user.login.queue","user.login.topic.change","user.login"),
	;
	
	/**队列命名*/
	private String queueName;
	/**交换机命名*/
	private String exChangeName;
	/**路由命名( 标识--<队列与交换机的绑定>)*/
	private String routingKey;
	
	private RabbitRoutingEnum(String queueName,String exChangeName, String routingKey) {
		this.queueName = queueName;
		this.exChangeName = exChangeName;
		this.routingKey = routingKey;
	}
	
}
