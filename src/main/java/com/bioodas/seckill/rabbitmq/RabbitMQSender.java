package com.bioodas.seckill.rabbitmq;

import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RabbitMQSender implements QueueMessageService,InitializingBean {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		/* 设置回调为当前类对象 */
		rabbitTemplate.setConfirmCallback(this);
	}

	@Override
	public void send(Object message, RabbitRoutingEnum routingEnum) throws Exception {
		rabbitTemplate.convertAndSend(routingEnum.getExChangeName(), routingEnum.getRoutingKey(), message,setConfirmCallback());
	}

	@Override
	public void send(Object message, MessageProperties properties, RabbitRoutingEnum routingEnum) throws Exception {
		Message msg = new Message(message.toString().getBytes(), properties);
		rabbitTemplate.convertAndSend(routingEnum.getExChangeName(),routingEnum.getRoutingKey() ,msg);
	}

	/** 消息回调确认方法 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if(correlationData==null) return;
		log.info("消息代号:{} {}", correlationData.getId(), ack == true ? "消息发送成功" : "消息发送失败:" + cause);
	}

	/** 设置回调为当前类对象 */
	private CorrelationData setConfirmCallback() {
		/* 构建回调id为UUID */
		CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
		log.info("发送MQ消息代号:{}", correlationData.getId());
		return correlationData;
	}
}
