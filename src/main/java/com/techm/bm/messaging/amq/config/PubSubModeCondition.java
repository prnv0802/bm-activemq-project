package com.techm.bm.messaging.amq.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class PubSubModeCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		boolean pubSubFlag = false;
		String mode = context.getEnvironment().getProperty("activemq.mode");
		String pubSubDomain = context.getEnvironment().getProperty("spring.jms.pub-sub-domain");
		
		if (mode.equals("pubsub") && pubSubDomain.equals("true")) {
			pubSubFlag = true;
		} else if (mode.equals("p2p")) {
			pubSubFlag = false;
		} else {
			throw new IllegalStateException("Invalid mode value or pubSubDomain, "
					+ "p2p and pubsub are only valid values " + "of activemq.mode property."
					+ " spring.jms.pub-sub-domain should be set to" + " true if pubsub mode is desired.");
		}
		System.out.println("PubSub condition is "+pubSubFlag);
		return pubSubFlag;
	}

}
