package com.example.demo.service;

import org.springframework.messaging.Message;

public class PrintService {

	public void print(Message<String> message) {
//		System.out.println(message.getPayload());
//		simulating error
		throw new RuntimeException("this is a simulation...");
	}
	
}
