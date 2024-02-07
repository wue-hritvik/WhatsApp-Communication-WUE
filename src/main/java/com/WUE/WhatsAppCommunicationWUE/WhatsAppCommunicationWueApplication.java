package com.WUE.WhatsAppCommunicationWUE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WhatsAppCommunicationWueApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhatsAppCommunicationWueApplication.class, args);
	}
   @Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
