package com.ecommerce.customer.repo;

import com.ecommerce.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByUsername(String username);
    Optional<Customer> findByEmail(String email);
}
