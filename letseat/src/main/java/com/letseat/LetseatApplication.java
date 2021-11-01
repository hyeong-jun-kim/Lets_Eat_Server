package com.letseat;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.letseat.configuration.EntityConfig;

@SpringBootApplication
public class LetseatApplication {

	public static void main(String[] args) {
		SpringApplication.run(LetseatApplication.class, args);
	}

}
