package com.techm.bm.messaging.amq.p2ptest;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
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

import static org.hamcrest.Matchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
public class SampleActiveMqTests {

	@ClassRule
	public static final OutputCapture output = new OutputCapture();

	@Autowired
	private MessageSender sender;

	@Test
	public void sendSimpleMessage() throws InterruptedException {
		this.sender.sendMessage(new DummyModel("Test", "1234"));
		Thread.sleep(1000L);
		assertThat(output.toString(),containsString("1234") );
	}

}