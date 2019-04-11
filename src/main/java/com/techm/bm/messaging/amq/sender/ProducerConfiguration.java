package com.techm.bm.messaging.amq.sender;

import java.util.Arrays;

import javax.jms.ConnectionFactory;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.converter.SimpleMessageConverter;

@Configuration
public class ProducerConfiguration {

	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;
	
	@Value("${topic.name1}")
	private String producerTopic;
	

	@Value("${session-cache-size}")
	private int sessionCacheSize;
	
	
	@Bean
	public ActiveMQConnectionFactory connectionFactory(){
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(brokerUrl);
		connectionFactory.setTrustedPackages(Arrays.asList("com.techm.bm.messaging.amq","com.techm.bm.messaging.amq.model"));
		return connectionFactory;
	}
	
	/*
     * Optionally you can use cached connection factory if performance is a big concern.
     */
 
    @Bean
    public ConnectionFactory cachingConnectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setTargetConnectionFactory(connectionFactory());
        connectionFactory.setSessionCacheSize(sessionCacheSize);
        return connectionFactory;
    }
    
	@Bean 
	public JmsTemplate jmsTemplate(){
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(producerTopic);
		return template;
	}

	@Bean
    MessageConverter converter(){
        return new SimpleMessageConverter();
    }
}
