package com.techm.bm.messaging.amq.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class PubSubModeCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		boolean pubSubFlag = false;
		String mode = context.getEnvironment().getProperty("activemq.mode");
		if (mode.equals("pubsub")) {
			pubSubFlag = true;
		} else if (mode.equals("p2p")) {
			pubSubFlag = false;
		} else {
			throw new IllegalStateException(
					"Invalid mode value, " + "p2p and pubsub are only valid values of activemq.mode property.");
		}
		System.out.println("PubSub condition is "+pubSubFlag);
		return pubSubFlag;
	}

}
