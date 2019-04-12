package com.techm.bm.messaging.amq.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class P2PModeCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		boolean p2pFlag = false;
		String mode = context.getEnvironment().getProperty("activemq.mode");
		String pubSubDomain = context.getEnvironment().getProperty("spring.jms.pub-sub-domain");

		if (mode.equals("p2p") && pubSubDomain.equals("false")) {
			p2pFlag = true;
		} else if (mode.equals("pubsub")) {
			p2pFlag = false;
		} else {
			throw new IllegalStateException("Invalid mode value or pubSubDomain, "
					+ "p2p and pubsub are only valid values " + "of activemq.mode property."
					+ " spring.jms.pub-sub-domain should be set to" + " false if p2p mode is desired.");
		}
		System.out.println("P2P condition is " + p2pFlag);
		return p2pFlag;
	}

}
