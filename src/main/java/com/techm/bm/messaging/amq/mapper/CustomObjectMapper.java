package com.techm.bm.messaging.amq.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(CustomObjectMapper.class);

	public CustomObjectMapper() {
		super();
		LOG.info("CustomObjectMapper invoked....");
		this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
	}

}
