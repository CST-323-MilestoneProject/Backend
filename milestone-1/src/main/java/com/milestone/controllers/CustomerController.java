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

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        logger.info("Entering getAllCustomers method");
        List<Customer> customers = customerRepository.findAll();
        logger.info("Exiting getAllCustomers method");
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

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

    @PostMapping
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer) {
        logger.info("Entering createNewCustomer method with customer: {}", customer);
        Customer savedCustomer = customerRepository.save(customer);
        logger.info("Exiting createNewCustomer method with saved customer: {}", savedCustomer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        logger.info("Entering deleteCustomer method with id: {}", id);
        customerRepository.deleteById(id);
        logger.info("Exiting deleteCustomer method");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    public void testController() {
        logger.info("Entering testController method");
        System.out.println("test successful");
        logger.info("Exiting testController method");
    }
}
