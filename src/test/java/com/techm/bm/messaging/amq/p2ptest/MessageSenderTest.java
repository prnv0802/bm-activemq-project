package com.techm.bm.messaging.amq.p2ptest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techm.bm.messaging.amq.consumer.MessageReceiver;
import com.techm.bm.messaging.amq.model.DummyModel;
import com.techm.bm.messaging.amq.sender.MessageSender;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MessageSenderTest {

	@Autowired
	MessageSender sender;
	
	
	@Test
	public void testOnlySender()
	{
		sender.sendMessage(new DummyModel(null, null));
		
	}
	
	
	
	
}
