package com.ecommerce.customer.service;

import com.ecommerce.customer.dto.CustomerRegisterRequest;
import com.ecommerce.customer.model.Customer;
import com.ecommerce.customer.repo.CustomerRepository;
import com.ecommerce.user.model.Role;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepo;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public void registerCustomer(CustomerRegisterRequest dto) {
        Customer customer = Customer.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .contactNumber(dto.getContactNumber())
                .addresses(dto.getAddresses())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        customerRepo.save(customer);

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.CUSTOMER);
        userRepo.save(user);
    }

    public Customer getCustomer(UUID id) {
        return customerRepo.findById(id).orElseThrow();
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public void deleteCustomer(UUID id) {
        customerRepo.deleteById(id);
        userRepo.deleteById(id);
    }

    public Customer updateCustomer(UUID id, Customer updated) {
        Customer existing = customerRepo.findById(id).orElseThrow();

        existing.setName(updated.getName());
        existing.setContactNumber(updated.getContactNumber());
        existing.setEmail(updated.getEmail());
        existing.setUpdatedAt(Instant.now());

        return customerRepo.save(existing);
    }

    public Customer addAddress(UUID id, Customer.Address address) {
        Customer customer = customerRepo.findById(id).orElseThrow();
        customer.getAddresses().add(address);
        customer.setUpdatedAt(Instant.now());

        return customerRepo.save(customer);
    }

    public void addToWishlist(UUID customerId, UUID productId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow();
        if (!customer.getWishlist().contains(productId)) {
            customer.getWishlist().add(productId);
            customer.setUpdatedAt(Instant.now());
            customerRepo.save(customer);
        }
    }

    public void removeFromWishlist(UUID customerId, UUID productId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow();
        if (customer.getWishlist().remove(productId)) {
            customer.setUpdatedAt(Instant.now());
            customerRepo.save(customer);
        }
    }

    public void addToRecentlyViewed(UUID customerId, UUID productId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow();
        List<UUID> viewed = customer.getRecentlyViewed();

        viewed.remove(productId); // Ensure uniqueness
        viewed.add(0, productId); // Add to front
        if (viewed.size() > 10) { // Limit size
            viewed.remove(viewed.size() - 1);
        }

        customer.setUpdatedAt(Instant.now());
        customerRepo.save(customer);
    }


}
