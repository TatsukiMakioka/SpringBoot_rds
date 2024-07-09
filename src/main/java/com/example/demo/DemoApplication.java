package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point of the Spring Boot application.
 * This class contains the main method which uses Spring Boot's SpringApplication.run() method to launch the application.
 */
@SpringBootApplication
public class DemoApplication {

	/**
	 * The main method that serves as the entry point for the application.
	 * It delegates to Spring Boot's SpringApplication class and calls run.
	 *
	 * @param args Array of strings representing the command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}