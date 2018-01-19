package com.bioodas.seckill.rabbitmq;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RabbitMQSender implements QueueMessageService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void send(Object message, RabbitRoutingEnum routingEnum) throws Exception {
		/* 设置回调为当前类对象 */
		rabbitTemplate.setConfirmCallback(this);
		/* 构建回调id为UUID */
		CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
		log.info("发送MQ消息代号:{}",correlationId);
		/* 发送消息到消息队列 */
		rabbitTemplate.convertAndSend(routingEnum.getExChangeName(), routingEnum.getRoutingKey(), message, correlationId);
	}

	/** 消息回调确认方法 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		log.info("消息代号:{} {}", correlationData.getId(),ack == true ? "消息发送成功" :"消息发送失败:"+cause);
	}

}
