package com.ecommerce.vendor.service;

import com.ecommerce.user.model.Role;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repo.UserRepository;
import com.ecommerce.vendor.dto.VendorRegisterRequest;
import com.ecommerce.vendor.model.Vendor;
import com.ecommerce.vendor.repo.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VendorService {

    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerVendor(VendorRegisterRequest dto, MultipartFile governmentIdFile) {
        if (dto.getPassword() == null) {
            throw new IllegalArgumentException("Password is required");
        }

        // Save Vendor entity
        Vendor vendor = new Vendor();
        vendor.setUsername(dto.getUsername());
        vendor.setEmail(dto.getEmail());
        vendor.setPassword(passwordEncoder.encode(dto.getPassword()));
        vendor.setName(dto.getName());
        vendor.setContactNumber(dto.getContactNumber());

        try {
            vendor.setGovernmentId(governmentIdFile.getBytes());
            vendor.setGovernmentIdContentType(governmentIdFile.getContentType());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read government ID file", e);
        }

        vendor.setCreatedAt(Instant.now());
        vendor.setUpdatedAt(Instant.now());
        vendorRepository.save(vendor);

        // Create corresponding User
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.VENDOR);
        userRepository.save(user);
    }

    public Vendor getVendor(UUID id) {
        return vendorRepository.findById(id).orElseThrow();
    }

    public Vendor updateVendor(UUID id, Vendor updated) {
        Vendor existing = vendorRepository.findById(id).orElseThrow();
        existing.setName(updated.getName());
        existing.setContactNumber(updated.getContactNumber());
        existing.setEmail(updated.getEmail());
        return vendorRepository.save(existing);
    }

    public void deleteVendor(UUID id) {
        vendorRepository.deleteById(id);
        userRepository.deleteById(id);
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

}
