package me.shijunjie.producer;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service("messageProducer")
public class MessageProducer {

	@Resource(name = "amqpTemplate")
	private AmqpTemplate amqpTemplate;

	public void sendMessage(Object message) throws IOException {
		System.out.println("to send message:{}" + message);
		amqpTemplate.convertAndSend("queueTestKey", message);
		amqpTemplate.convertAndSend("queueTestChris", message);
	}
}
