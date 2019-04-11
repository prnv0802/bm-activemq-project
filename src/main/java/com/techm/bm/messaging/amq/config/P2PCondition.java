package com.techm.bm.messaging.amq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

public class P2PCondition implements Condition {

//	@Value("${activemq.mode}")
//	private String mode;

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		boolean p2pFlag = false;
		String mode = context.getEnvironment().getProperty("activemq.mode");
		if (mode.equals("p2p") || mode.equals("both")) {
			p2pFlag = true;
		} else {
			p2pFlag = false;
		}
		System.out.println("P2P condition is "+p2pFlag);
		return p2pFlag;
	}

}
