package com.milestone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.milestone.controllers.*;

/**
 * Main class to bootstrap the Spring Boot application.
 */
@SpringBootApplication
public class Milestone1Application {

    public static void main(String[] args) {
        SpringApplication.run(Milestone1Application.class, args);
        
        // Add a logging statement to check if the context is initialized
        System.out.println("Application context initialized. Checking if CustomerController is registered.");

        //tests
        CustomerController customerController = new CustomerController();
        customerController.testController();
    }

}
