package com.ecommerce.customer.controller;

import com.ecommerce.customer.dto.CustomerRegisterRequest;
import com.ecommerce.customer.model.Customer;
import com.ecommerce.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(summary = "Register a new customer")
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerRegisterRequest request) {
        customerService.registerCustomer(request);
        return ResponseEntity.ok("Customer registered successfully");
    }

    @Operation(summary = "Get customer by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(
            @PathVariable("id")
            @Parameter(
                    name = "id",
                    description = "Customer UUID",
                    required = true,
                    example = "d9b1f0b6-8dc9-4af5-8f70-8de158d1d82e",
                    schema = @Schema(type = "string", format = "uuid")
            )
            UUID id
    ) {
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @Operation(summary = "Get all customers")
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Operation(summary = "Delete customer by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable("id")
            @Parameter(
                    name = "id",
                    description = "Customer UUID",
                    required = true,
                    example = "d9b1f0b6-8dc9-4af5-8f70-8de158d1d82e",
                    schema = @Schema(type = "string", format = "uuid")
            )
            UUID id
    ) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update customer by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable("id")
            @Parameter(
                    name = "id",
                    description = "Customer UUID",
                    required = true,
                    example = "d9b1f0b6-8dc9-4af5-8f70-8de158d1d82e",
                    schema = @Schema(type = "string", format = "uuid")
            )
            UUID id,
            @RequestBody Customer updatedCustomer
    ) {
        return ResponseEntity.ok(customerService.updateCustomer(id, updatedCustomer));
    }

    @Operation(summary = "Add address to customer")
    @PostMapping("/{id}/addresses")
    public ResponseEntity<Customer> addAddress(
            @PathVariable("id")
            @Parameter(
                    name = "id",
                    description = "Customer UUID",
                    required = true,
                    example = "d9b1f0b6-8dc9-4af5-8f70-8de158d1d82e",
                    schema = @Schema(type = "string", format = "uuid")
            )
            UUID id,
            @RequestBody Customer.Address newAddress
    ) {
        return ResponseEntity.ok(customerService.addAddress(id, newAddress));
    }
}
