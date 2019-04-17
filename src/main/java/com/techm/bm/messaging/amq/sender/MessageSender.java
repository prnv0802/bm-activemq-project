package com.techm.bm.messaging.amq.sender;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.techm.bm.messaging.amq.model.DummyModel;

@Component
public class MessageSender {

	@Autowired
	JmsTemplate jmsTemplate;

	public void sendMessage(final DummyModel model) {

		System.out.println("MessageSender: DummyModel to destination : " + jmsTemplate.getDefaultDestinationName());
		
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session.createObjectMessage(model);
				return objectMessage;
			}
		});
	}

}
