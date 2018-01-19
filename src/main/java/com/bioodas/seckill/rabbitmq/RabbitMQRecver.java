package com.bioodas.seckill.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RabbitMQRecver {
	
	@RabbitListener(queues = "user.login.queue")
	public void onMessage(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws Exception{
		System.err.println(channel);
		log.info("该用户完成了登录:{}",message);
		channel.basicAck(deliveryTag,false);
	}
}
