package com.example.demo.service;

import org.springframework.messaging.Message;

public class UppercasePrintService {

	public void print(Message<String> message) {
		System.out.println(message.getPayload().toUpperCase());
	}
	
}
