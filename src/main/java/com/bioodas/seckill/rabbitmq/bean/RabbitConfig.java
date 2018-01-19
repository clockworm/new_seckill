package com.bioodas.seckill.rabbitmq.bean;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bioodas.seckill.rabbitmq.RabbitRoutingEnum;

/**
 * @author TangLingYun
 * @detail 路由绑定Bean (实例队列与交互器的绑定)
 */
@Configuration
public class RabbitConfig{

	/** 初始化用户登录路由绑定配置 */
	@Bean
	public Queue userLoginQueue(){
		return new Queue(RabbitRoutingEnum.USER_LOGIN_ROUTING.getQueueName());
	}
	
	@Bean
	public DirectExchange userLoginExChange(){
		return new DirectExchange(RabbitRoutingEnum.USER_LOGIN_ROUTING.getChangeName());
	}
	
	@Bean
	public Binding userLoginBinding() {
		String routingKey = RabbitRoutingEnum.USER_LOGIN_ROUTING.getRoutingKey();
		return BindingBuilder.bind(userLoginQueue()).to(userLoginExChange()).with(routingKey);
	}

}
