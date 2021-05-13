package com.reddivestor.reddivestorapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReddivestorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReddivestorApiApplication.class, args);
		System.out.println("Ready for business.");
	}

}
