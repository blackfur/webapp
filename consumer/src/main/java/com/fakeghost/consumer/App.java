package com.fakeghost.consumer;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientException;

@SpringBootApplication
public class App {

	public static void main(String[] args) throws RestClientException, IOException {
		ApplicationContext ctx = SpringApplication.run(
				App.class, args);
		
		ConsumerCtrlClient consumerControllerClient=ctx.getBean(ConsumerCtrlClient.class);
		consumerControllerClient.getEmployee();
		
	}
	
	@Bean
	public  ConsumerCtrlClient  consumerControllerClient()
	{
		return  new ConsumerCtrlClient();
	}
}
