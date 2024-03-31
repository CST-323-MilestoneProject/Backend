package com.milestone.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.milestone.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

}