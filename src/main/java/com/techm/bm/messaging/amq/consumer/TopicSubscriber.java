package com.techm.bm.messaging.amq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.techm.bm.messaging.amq.mapper.CustomObjectMapper;

@Component
public class TopicSubscriber {

	private static final Logger LOGGER = LoggerFactory.getLogger(TopicSubscriber.class);

	@Autowired
	CustomObjectMapper mapper;
	
	@JmsListener(destination = "${topic.name2}")
	public void listen(@Payload String payload) {
		LOGGER.info("TopicSubscriber : listen : " + payload);
		try {
			System.out.println("TopicSubscriber: Listening Payload String " + payload);

			// utilise the object mapper here to convert the 
			// message to your desired model.
			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
