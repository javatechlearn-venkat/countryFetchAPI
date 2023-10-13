package com.acnt.java.country;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * The Class CountryApplication. Author : Venkat
 */
@SpringBootApplication
public class CountryExternalApiApplication {

	/**
	 * The main method to boot the country application.
	 *
	 */
	public static void main(String[] args) {
		SpringApplication.run(CountryExternalApiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
