package com.techm.bm.messaging.amq.config;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import com.techm.bm.messaging.amq.consumer.SubscriberErrorHandler;

@Configuration
public class MessagingListnerConfiguration {

	@Autowired
	ConnectionFactory connectionFactory;
	
	@Autowired
	SubscriberErrorHandler subscriberErrorHandler;

	@Value("${spring.jms.pub-sub-domain}")
	private boolean pubSubDomain;
	
	@Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(pubSubDomain);
        factory.setConcurrency("1-1");
        factory.setErrorHandler(subscriberErrorHandler);
        return factory;
    }

}
