//package com.techm.bm.messaging.amq.sender;
//
//import javax.jms.JMSException;
//import javax.jms.Topic;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsMessagingTemplate;
//import org.springframework.stereotype.Component;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.techm.bm.messaging.amq.mapper.CustomObjectMapper;
//
//@Component
//public class TopicPublisher {
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(TopicPublisher.class);
//
//	@Autowired
//	private JmsMessagingTemplate jmsMessagingTemplate;
//
//	@Autowired
//	private Topic genericTopic;
//
//	@Autowired
//	private CustomObjectMapper objectMapper;
//	
//	public void send(Object genericObject) {
//		
//		try {
//			String payload = objectMapper.writeValueAsString(genericObject);
//
//			LOGGER.info("sending payload='{}' to topic='{}'", payload, genericTopic.getTopicName());
//			jmsMessagingTemplate.convertAndSend(genericTopic, payload);
//			
//		} catch (JsonProcessingException e) {
//			LOGGER.error("Unabel to convert Object to JSON", e);
//		} catch (JMSException e) {
//			LOGGER.error("Unabel to send message to active mq", e);
//		} catch (Exception e) {
//			LOGGER.error("Error while updating product catalog specification", e);
//		}
//	}
//
//
//
//}
