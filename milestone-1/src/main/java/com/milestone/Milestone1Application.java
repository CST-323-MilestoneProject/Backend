package com.milestone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.milestone.controllers.*;

@SpringBootApplication
public class Milestone1Application {

	public static void main(String[] args) {
		SpringApplication.run(Milestone1Application.class, args);
		
        // Add a logging statement to check if the context is initialized
        System.out.println("Application context initialized. Checking if CustomerController is registered.");

        // You can also directly create an instance of the controller and call a method for testing
        // For example, assuming there's a method named testController in CustomerController
        // Uncomment the following lines for testing
        
        CustomerController customerController = new CustomerController();
        customerController.testController();
        
	}

}
