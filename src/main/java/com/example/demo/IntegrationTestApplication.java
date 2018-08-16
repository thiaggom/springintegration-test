package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

@SpringBootApplication
@Configuration
@ImportResource("integration-context.xml")
public class IntegrationTestApplication implements ApplicationRunner{

	@Autowired
	@Qualifier("channel1In")
	private DirectChannel directInChannel;
	
	@Autowired
	@Qualifier("channel1Out")
	private DirectChannel directOutChannel;
	
	public static void main(String[] args) {
		SpringApplication.run(IntegrationTestApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		directOutChannel.subscribe(new MessageHandler() {
			
			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				System.out.println(message.getPayload());
				
			}
		});
		
		Message<String> message = MessageBuilder.withPayload("Message created successfully!").setHeader("header1", "Teste header").build(); 
		directInChannel.send(message);
	}
	
	
}
