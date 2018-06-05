package com.rvilardo.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class StoreApplication {

    public static final String API_VERSION = "1.0";
    
	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}
}
