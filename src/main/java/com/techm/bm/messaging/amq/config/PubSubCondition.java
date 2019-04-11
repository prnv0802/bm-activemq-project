package com.techm.bm.messaging.amq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

public class PubSubCondition implements Condition {

//	@Value("${activemq.mode}")
//	private String mode;

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		boolean pubSubFlag = false;
		String mode = context.getEnvironment().getProperty("activemq.mode");
		if (mode.equals("pubsub") || mode.equals("both")) {
			pubSubFlag = true;
		} else {
			pubSubFlag = false;
		}
		System.out.println("PubSub condition is "+pubSubFlag);
		return pubSubFlag;
	}

}
