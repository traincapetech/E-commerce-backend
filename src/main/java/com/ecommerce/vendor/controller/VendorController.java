package com.ecommerce.vendor.controller;

import com.ecommerce.vendor.dto.VendorRegisterRequest;
import com.ecommerce.vendor.model.Vendor;
import com.ecommerce.vendor.service.VendorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vendors")
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Operation(summary = "Register a new vendor")
    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> registerVendor(
            @RequestPart("vendor")
            @Parameter(description = "Vendor registration details in JSON format")
            String vendorJson,

            @RequestPart("governmentId")
            @Parameter(description = "Government ID file (PDF/image)")
            MultipartFile governmentIdFile
    ) throws IOException {

        VendorRegisterRequest vendorRequest = objectMapper.readValue(vendorJson, VendorRegisterRequest.class);
        vendorService.registerVendor(vendorRequest, governmentIdFile);

        return ResponseEntity.ok("Vendor registered successfully");
    }

    @Operation(summary = "Get vendor by ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getVendor(
            @PathVariable("id")
            @Parameter(
                    name = "id",
                    description = "UUID of the vendor to retrieve",
                    required = true,
                    example = "b4fa9c02-6c74-4e53-b5ef-287b4f84e559",
                    schema = @Schema(type = "string", format = "uuid")
            )
            UUID id
    ) {
        return ResponseEntity.ok(vendorService.getVendor(id));
    }

    @Operation(summary = "Update vendor by ID")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVendor(
            @PathVariable("id")
            @Parameter(
                    name = "id",
                    description = "UUID of the vendor to update",
                    required = true,
                    example = "b4fa9c02-6c74-4e53-b5ef-287b4f84e559",
                    schema = @Schema(type = "string", format = "uuid")
            )
            UUID id,

            @RequestBody VendorRegisterRequest vendorRequest
    ) {
        Vendor vendor = new Vendor();
        vendor.setId(id);
        vendor.setUsername(vendorRequest.getUsername());
        vendor.setEmail(vendorRequest.getEmail());
        vendor.setPassword(vendorRequest.getPassword());
        vendor.setName(vendorRequest.getName());
        vendor.setContactNumber(vendorRequest.getContactNumber());

        return ResponseEntity.ok(vendorService.updateVendor(id, vendor));
    }

    @Operation(summary = "Delete vendor by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(
            @PathVariable("id")
            @Parameter(
                    name = "id",
                    description = "UUID of the vendor to delete",
                    required = true,
                    example = "b4fa9c02-6c74-4e53-b5ef-287b4f84e559",
                    schema = @Schema(type = "string", format = "uuid")
            )
            UUID id
    ) {
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all vendors")
    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendors = vendorService.getAllVendors();
        return ResponseEntity.ok(vendors);
    }
}
