package com.challenge.mindata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class MindataApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindataApplication.class, args);
	}

}
