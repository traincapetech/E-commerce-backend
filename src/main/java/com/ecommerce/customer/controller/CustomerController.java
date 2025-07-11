package com.ecommerce.customer.controller;

import com.ecommerce.customer.dto.CustomerRegisterRequest;
import com.ecommerce.customer.model.Customer;
import com.ecommerce.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerRegisterRequest request) {
        customerService.registerCustomer(request);
        return ResponseEntity.ok("Customer registered successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(
            @Parameter(description = "Customer ID") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @Parameter(description = "Customer ID") @PathVariable UUID id
    ) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable UUID id,
            @RequestBody Customer updatedCustomer
    ) {
        return ResponseEntity.ok(customerService.updateCustomer(id, updatedCustomer));
    }

    @PostMapping("/{id}/addresses")
    public ResponseEntity<Customer> addAddress(
            @PathVariable UUID id,
            @RequestBody Customer.Address newAddress
    ) {
        return ResponseEntity.ok(customerService.addAddress(id, newAddress));
    }

}
