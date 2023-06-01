package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan 
public class SpringBootIntro1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIntro1Application.class, args);
	}

}
