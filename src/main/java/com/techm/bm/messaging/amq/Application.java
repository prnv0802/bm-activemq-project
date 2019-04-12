package com.techm.bm.messaging.amq;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.techm.bm.messaging.amq.model.DummyModel;
import com.techm.bm.messaging.amq.sender.MessageSender;
import com.techm.bm.messaging.amq.sender.TopicPublisher;

@SpringBootApplication
@ComponentScan("com.techm.bm.messaging.amq.*")
@EnableScheduling
@EnableJms
public class Application {
	

	public static void main(String[] args) {


		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		// case 1 : mode p2p, jmpPubSUb false, Using MessageSender queueconfig works 
		//context.getBean(MessageSender.class).sendMessage(new DummyModel("Pranav", "1"));
//		
		// case 2 : mode pubsub, Using TopicPublisher, TopicSubscriber Listens String payload
		//context.getBean(TopicPublisher.class).send(new DummyModel("Pranav", "1"));
		
		// case 3: mode pubsub, Using Message Sender to send model, TopicSubscriber2 listens as it listens model. 
		//context.getBean(MessageSender.class).sendMessage(new DummyModel("Pranav", "1"));
		
		// case 4 : mode both, Using TopicPublisher, nothing on MessageReceiver, All TopicSubscribers Listening
		//context.getBean(TopicPublisher.class).send(new DummyModel("Pranav", "1"));
		
		// case 5 : mode pubsub, Using MessageSender
		context.getBean(MessageSender.class).sendMessage(new DummyModel("Pranav", "1"));
		
		try {
			Thread.sleep(10);
			System.out.println("Sleeping 10 ms after Publisher 2");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
    
//    @Bean
//    public DefaultMessageHandlerMethodFactory handlerMethodFactory() {
//        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
//        factory.setMessageConverter((org.springframework.messaging.converter.MessageConverter) messageConverter());
//        return factory;
//    }
//
//    @Bean
//    public MappingJackson2MessageConverter messageConverter() {
//        return new MappingJackson2MessageConverter();
//    }
//
//    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
//        registrar.setMessageHandlerMethodFactory(handlerMethodFactory());
//    }
    
	@Bean
	public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory(
			@Value("${server.port}") final String port,
			@Value("${jetty.threadPool.maxThreads}") final String maxThreads,
			@Value("${jetty.threadPool.minThreads:8}") final String minThreads,
			@Value("${jetty.threadPool.idleTimeout:60000}") final String idleTimeout) {
		final JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory(
				Integer.valueOf(port));
		factory.addServerCustomizers(new JettyServerCustomizer() {
			public void customize(final Server server) {
				// Tweak the connection pool used by Jetty to handle incoming HTTP connections
				final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
				threadPool.setMaxThreads(Integer.valueOf(maxThreads));
				threadPool.setMinThreads(Integer.valueOf(minThreads));
				threadPool.setIdleTimeout(Integer.valueOf(idleTimeout));
			}
		});
		return factory;
	}

}
