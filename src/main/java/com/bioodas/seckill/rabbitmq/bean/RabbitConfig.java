package com.bioodas.seckill.rabbitmq.bean;

import java.util.HashMap;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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
	public TopicExchange userLoginExchange(){
		return new TopicExchange(RabbitRoutingEnum.USER_LOGIN_ROUTING.getExChangeName());
	}
	
	@Bean
	public Binding userLoginBinding() {
		String routingKey = RabbitRoutingEnum.USER_LOGIN_ROUTING.getRoutingKey();
		return BindingBuilder.bind(userLoginQueue()).to(userLoginExchange()).with(routingKey);
	}

	@Bean
	public Queue headersQueue(){
		return new Queue(RabbitRoutingEnum.HEADERS_ROUTING.getQueueName());
	}
	
	@Bean
	public HeadersExchange headersExchange(){
		return new HeadersExchange(RabbitRoutingEnum.HEADERS_ROUTING.getExChangeName());
	}
	
	@Bean
	public Binding headersBinding(){
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("sendInstruction", true);
		return BindingBuilder.bind(headersQueue()).to(headersExchange()).whereAll(map).match();
	}
	
	
	@Bean
	public Queue productKillQueue(){
		return new Queue(RabbitRoutingEnum.PRODUCT_KILL_ROUTING.getQueueName());
	}
	
	@Bean
	public TopicExchange productKillExchange(){
		return new TopicExchange(RabbitRoutingEnum.PRODUCT_KILL_ROUTING.getExChangeName());
	}
	
	@Bean
	public Binding productKillBinding() {
		String routingKey = RabbitRoutingEnum.PRODUCT_KILL_ROUTING.getRoutingKey();
		return BindingBuilder.bind(productKillQueue()).to(productKillExchange()).with(routingKey);
	}
	
}
