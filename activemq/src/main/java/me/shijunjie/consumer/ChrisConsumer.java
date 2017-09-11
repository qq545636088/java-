package me.shijunjie.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class ChrisConsumer implements MessageListener{

	  @Override  
	    public void onMessage(Message message) {  
	       System.out.println("chris receive message------->:{}"+new String(message.getBody()));  
	    }  
}
