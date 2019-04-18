package com.techm.bm.messaging.amq.p2ptest;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.techm.bm.messaging.amq.model.DummyModel;
import com.techm.bm.messaging.amq.sender.TopicPublisher;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties", properties = { "activemq.mode=pubsub",
		"spring.jms.pub-sub-domain=true" })
@DirtiesContext
public class PubSubTestUsingTopicPublisher {

	@ClassRule
	public static final OutputCapture output = new OutputCapture();
	
	/*@Rule
	public ExpectedException exception = ExpectedException.none();*/

	@Autowired
	private TopicPublisher publisher;

	@Test
	public void testJMSTemplate() {
		System.out.println(publisher);
		Assert.assertNotNull(publisher.getJmsTemplate());
		Assert.assertEquals("myTopic", publisher.getJmsTemplate().getDefaultDestinationName());
	}

	@Test
	public void testPubSubConditionAsTrue() {
		assertThat(output.toString(), containsString("PubSub condition is true"));
	}

	@Test
	public void testP2PConditionAsFalse() {
		assertThat(output.toString(), containsString("P2P condition is false"));
	}

	@Test
	public void sendSimpleMessage() throws InterruptedException {
		this.publisher.send(new DummyModel("Rajnikant", "007"));
		Thread.sleep(1000L);
		assertThat(output.toString(), containsString(
				"TopicPublisher: sending payload='{\"name\":\"Rajnikant\",\"id\":\"007\",\"age\":0}' to topic='myTopic'"));

		assertThat(output.toString(),
				containsString("TopicSubscriber : listen : {\"name\":\"Rajnikant\",\"id\":\"007\",\"age\":0}"));
		// Output of Subscriber 3
		assertThat(output.toString(), containsString("Text Message {\"name\":\"Rajnikant\",\"id\":\"007\",\"age\":0}"));
	}

	@Test
	public void sendNullMessage() throws InterruptedException {
		this.publisher.send(null);

		Thread.sleep(1000L);
		assertThat(output.toString(), containsString("TopicPublisher: sending payload='null' to topic='myTopic'"));
		assertThat(output.toString(), containsString("TopicSubscriber : listen : null"));
		// Output of Subscriber 3
		assertThat(output.toString(), containsString("Text Message null"));

	}

	// @Test
	public void sendNullPropsInModel() throws InterruptedException {
		this.publisher.send(new DummyModel(null, null));
		Thread.sleep(1000L);

		assertThat(output.toString(), containsString(
				"TopicPublisher: sending payload='{\"name\":null,\"id\":null,\"age\":0}' to topic='myTopic'"));

		assertThat(output.toString(),
				containsString("TopicSubscriber : listen : {\"name\":null,\"id\":null,\"age\":0}"));
		// Output of Subscriber 3
		assertThat(output.toString(), containsString("Text Message {\"name\":null,\"id\":null,\"age\":0}"));
	}

	//@Test
	public void testMessageSelector1() {
		JmsTemplate template = publisher.getJmsTemplate();

		//exception.expect(ListenerExecutionFailedException.class);
		
		template.convertAndSend(template.getDefaultDestinationName(), "Dummy String Message", mpp -> {
			mpp.setStringProperty("messageType", "DummyModel"); // notice the additional props set
			return mpp;
		});
		
	}

	@Test
	public void testMessageSelector2() {
		JmsTemplate template = publisher.getJmsTemplate();

		
		template.convertAndSend(template.getDefaultDestinationName(), new DummyModel("MessageSelector", "10"), mpp -> {
			mpp.setStringProperty("messageType", "DummyModel"); // notice the additional props set
			return mpp;
		});

		// TopicSubscriber 2 listens because selector property is DummyModel and
		// Listener is able to convert to DummyModel type which is used in publisher.
		assertThat(output.toString(),
				containsString("TopicSubscriber2: listen : model DummyModel [name=MessageSelector, id=10, age=0]"));

	}
}