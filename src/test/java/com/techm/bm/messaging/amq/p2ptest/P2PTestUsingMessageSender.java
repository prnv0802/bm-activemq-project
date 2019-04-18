package com.techm.bm.messaging.amq.p2ptest;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.techm.bm.messaging.amq.model.DummyModel;
import com.techm.bm.messaging.amq.sender.MessageSender;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties", properties = { "activemq.mode=p2p",
		"spring.jms.pub-sub-domain=false" })
public class P2PTestUsingMessageSender {

	@ClassRule
	public static final OutputCapture output = new OutputCapture();

	@Autowired
	private MessageSender sender;

	@Test
	public void testPubSubConditionAsFalse() {
		assertThat(output.toString(), containsString("PubSub condition is false"));
	}

	@Test
	public void testP2PConditionAsTrue() {
		assertThat(output.toString(), containsString("P2P condition is true"));
	}

	@Test
	public void sendSimpleMessage() throws InterruptedException {
		this.sender.sendMessage(new DummyModel("Test", "1234"));
		Thread.sleep(1000L);
		assertThat(output.toString(),
				containsString("MessageReceiver : response received : DummyModel [name=Test, id=1234, age=0]"));
	}

	@Test
	public void sendNullMessage() throws InterruptedException {
		this.sender.sendMessage(null);
		Thread.sleep(1000L);
		assertThat(output.toString(), containsString("MessageReceiver : response received : null"));
	}

	@Test
	public void sendNullPropsInModel() throws InterruptedException {
		this.sender.sendMessage(new DummyModel(null, null));
		Thread.sleep(1000L);
		assertThat(output.toString(),
				containsString("MessageReceiver : response received : DummyModel [name=null, id=null, age=0]"));
	}
}