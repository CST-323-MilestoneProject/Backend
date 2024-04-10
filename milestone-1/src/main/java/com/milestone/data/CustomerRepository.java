package com.milestone.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.milestone.model.Customer;

/**
 * Repository interface for managing customer entities.
 * Extends JpaRepository to inherit basic CRUD operations.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
