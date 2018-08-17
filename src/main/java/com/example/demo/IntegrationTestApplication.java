package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import com.example.demo.service.PrinterGateway;

@SpringBootApplication
@Configuration
@ImportResource("integration-context.xml")
public class IntegrationTestApplication implements ApplicationRunner{

	@Autowired
	private PrinterGateway gateway;
	
	public static void main(String[] args) {
		SpringApplication.run(IntegrationTestApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		List<Future<Message<String>>> futures = new ArrayList<>();
		
		for (int x = 1; x <= 10; x++) {
			Message<String> message = MessageBuilder.withPayload("Printing message nº "+x)
					.setHeader("messageNumber", x).build();
			
			System.out.println("Seding message "+x);
			futures.add(this.gateway.print(message));
		}
		
		for (Future<Message<String>> future : futures) {
			System.out.println(future.get().getPayload());
		}
	}
	
	
}
