package com.bioodas.seckill.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author TangLingYun
 * @detail 消息队列业务
 */
public interface QueueMessageService extends RabbitTemplate.ConfirmCallback {
	
	
	/**
     * 发送消息到RabbitMQ消息队列
     * @param message 消息内容
     * @param RabbitRoutingEnum MQ路由配置枚举
     * @throws Exception
     */
    public void send(Object message, RabbitRoutingEnum routingEnum) throws Exception;
}

