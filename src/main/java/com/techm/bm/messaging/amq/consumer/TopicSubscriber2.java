package com.techm.bm.messaging.amq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.techm.bm.messaging.amq.mapper.CustomObjectMapper;
import com.techm.bm.messaging.amq.model.DummyModel;

@Component
public class TopicSubscriber2 {

	private static final Logger LOGGER = LoggerFactory.getLogger(TopicSubscriber2.class);

	@Autowired
	CustomObjectMapper mapper;
	
	@JmsListener(destination = "${topic.name2}")
	public void listen(DummyModel model) {
		LOGGER.info("Message : listen : model " + model);
		try {
			System.out.println("TopicSubscriber2: Listening Message model" + model);

			// utilise the object mapper here to convert the 
			// message to your desired model.
			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
