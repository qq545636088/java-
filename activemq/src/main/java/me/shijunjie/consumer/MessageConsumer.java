package me.shijunjie.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MessageConsumer implements MessageListener {
	    @Override
	    public void onMessage(Message message) {
	         System.out.println("consumer receive message------->:{}"+message);  
	        
	    }
}
