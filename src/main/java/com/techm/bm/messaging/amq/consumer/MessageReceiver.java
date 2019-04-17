package com.techm.bm.messaging.amq.consumer;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import com.techm.bm.messaging.amq.model.DummyModel;

@Component
public class MessageReceiver {

	static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);

	@JmsListener(destination = "${receiver-queue}")
	public void receiveMessage(final Message<DummyModel> message) throws JMSException {
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		MessageHeaders headers = message.getHeaders();
		LOG.info("MessageReceiver : headers received : {}", headers);

		DummyModel response = message.getPayload();
		LOG.info("MessageReceiver : response received : {}", response);
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
}
