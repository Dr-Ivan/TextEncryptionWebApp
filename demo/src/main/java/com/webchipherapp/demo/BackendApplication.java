package com.webchipherapp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.webchipherapp.demo.ControllerFiles")
@ComponentScan(basePackages = "com.webchipherapp.demo.Model")
@ComponentScan(basePackages = "com..webchipherapp.demo.DataTransferObjects")
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
