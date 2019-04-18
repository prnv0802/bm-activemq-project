package com.techm.bm.messaging.amq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

@Service
public class SubscriberErrorHandler implements ErrorHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberErrorHandler.class);
	@Override
	public void handleError(Throwable t) {
		LOGGER.error("Exception Occured while Subscribing to message", t.getCause());
		//t.printStackTrace();
		
	}
}