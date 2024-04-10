package com.milestone.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.milestone.data.CustomerRepository;
import com.milestone.model.Customer;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to customers.
 */
@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*") // Allow requests from any origin
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Endpoint for retrieving all customers.
     * @return ResponseEntity containing a list of customers and HTTP status code.
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        logger.info("Entering getAllCustomers method");
        List<Customer> customers = customerRepository.findAll();
        logger.info("Exiting getAllCustomers method");
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    /**
     * Endpoint for retrieving a customer by ID.
     * @param id The ID of the customer to retrieve.
     * @return ResponseEntity containing the customer and HTTP status code.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        logger.info("Entering getCustomerById method with id: {}", id);
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            logger.info("Exiting getCustomerById method with customer: {}", customer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            logger.warn("Customer not found with id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint for creating a new customer.
     * @param customer The customer object to create.
     * @return ResponseEntity containing the created customer and HTTP status code.
     */
    @PostMapping
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer) {
        logger.info("Entering createNewCustomer method with customer: {}", customer);
        Customer savedCustomer = customerRepository.save(customer);
        logger.info("Exiting createNewCustomer method with saved customer: {}", savedCustomer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    /**
     * Endpoint for updating an existing customer.
     * @param id The ID of the customer to update.
     * @param updatedCustomer The updated customer object.
     * @return ResponseEntity containing the updated customer and HTTP status code.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customer> editCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        logger.info("Entering editCustomer method with id: {} and updatedCustomer: {}", id, updatedCustomer);
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            // Update customer fields
            customer.setCustomerDetails(updatedCustomer.getCustomerDetails());
            customer.setFirstName(updatedCustomer.getFirstName());
            customer.setLastName(updatedCustomer.getLastName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setPhoneNumber(updatedCustomer.getPhoneNumber());

            customerRepository.save(customer);
            logger.info("Exiting editCustomer method with updated customer: {}", customer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            logger.warn("Customer not found with id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint for deleting a customer by ID.
     * @param id The ID of the customer to delete.
     * @return ResponseEntity with HTTP status code indicating success or failure.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        logger.info("Entering deleteCustomer method with id: {}", id);
        customerRepository.deleteById(id);
        logger.info("Exiting deleteCustomer method");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    /**
     * Method for testing the controller.
     */
    public void testController() {
        logger.info("Entering testController method");
        System.out.println("test successful");
        logger.info("Exiting testController method");
    }
}
