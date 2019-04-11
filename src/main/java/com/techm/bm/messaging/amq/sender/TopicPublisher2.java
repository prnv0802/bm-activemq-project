package com.techm.bm.messaging.amq.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techm.bm.messaging.amq.mapper.CustomObjectMapper;

@Component
public class TopicPublisher2 {

	private static final Logger LOGGER = LoggerFactory.getLogger(TopicPublisher2.class);

	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	private CustomObjectMapper objectMapper;
	
	public void send(Object genericObject) {
		
		try {
			String payload = objectMapper.writeValueAsString(genericObject);

			LOGGER.info("sending payload='{}' to topic='{}'", payload, jmsTemplate.getDefaultDestinationName());
			jmsTemplate.convertAndSend(jmsTemplate.getDefaultDestinationName(), payload);
			
		} catch (JsonProcessingException e) {
			LOGGER.error("Unabel to convert Object to JSON", e);
		} catch (Exception e) {
			LOGGER.error("Error while updating product catalog specification", e);
		}
	}



}
