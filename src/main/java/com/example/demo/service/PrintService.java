package com.example.demo.service;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

public class PrintService {

	public Message<?> print(Message<String> message) {
		System.out.println(message.getHeaders());
		System.out.println(message.getPayload());
		
		return MessageBuilder.withPayload("sending message to the out channel...").build();
	}
	
}
