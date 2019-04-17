package com.techm.bm.messaging.amq.consumer;

import javax.jms.Message;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.techm.bm.messaging.amq.mapper.CustomObjectMapper;

@Component
public class TopicSubscriber3 {

	private static final Logger LOGGER = LoggerFactory.getLogger(TopicSubscriber3.class);

	@Autowired
	CustomObjectMapper mapper;

	/**
	 * 
	 * @param message
	 *            of type Message. No selector example.
	 */
	@JmsListener(destination = "${topic.name2}")
	public void listen(Message message) {
		LOGGER.info("TopicSubscriber3: listen Message " + message);
		try {
			System.out.println("TopicSubscriber3: Listening Message" + message);

			if (message instanceof ActiveMQTextMessage) {
				System.out.println("Text Message " + ((ActiveMQTextMessage) message).getText());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
