package com.techm.bm.messaging.amq.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techm.bm.messaging.amq.mapper.CustomObjectMapper;

@Component
public class TopicPublisher {

	private static final Logger LOGGER = LoggerFactory.getLogger(TopicPublisher.class);

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	private CustomObjectMapper objectMapper;

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public CustomObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void send(Object genericObject) {

		try {
			String payload = objectMapper.writeValueAsString(genericObject);

			LOGGER.info("TopicPublisher: sending payload='{}' to topic='{}'", payload,
					jmsTemplate.getDefaultDestinationName());

			// Note the use of MessagePostProcess passed as lambda
			jmsTemplate.convertAndSend(jmsTemplate.getDefaultDestinationName(), payload, mpp -> {
				mpp.setStringProperty("messageType", "String");
				return mpp;
			});

		} catch (JsonProcessingException e) {
			LOGGER.error("Unabel to convert Object to JSON", e);
		} catch (Exception e) {
			LOGGER.error("Exception occured.", e.getMessage());
		}
	}

	@Override
	public String toString() {
		return "TopicPublisher [jmsTemplate=" + jmsTemplate + ", objectMapper=" + objectMapper + "]";
	}
	
	

}
