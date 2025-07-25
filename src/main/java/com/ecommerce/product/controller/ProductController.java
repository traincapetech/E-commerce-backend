package com.ecommerce.product.controller;

import com.ecommerce.product.model.Product;
import com.ecommerce.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Add a new product")
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @Operation(summary = "Update product by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id")
            @Parameter(
                    name = "id",
                    description = "Product UUID",
                    required = true,
                    example = "c9a5d520-2b49-4a6c-b9b3-7f0e2a2c5a8e",
                    schema = @Schema(type = "string", format = "uuid")
            )
            UUID id,
            @RequestBody Product product
    ) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @Operation(summary = "Get product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(
            @PathVariable("id")
            @Parameter(
                    name = "id",
                    description = "Product UUID",
                    required = true,
                    example = "c9a5d520-2b49-4a6c-b9b3-7f0e2a2c5a8e",
                    schema = @Schema(type = "string", format = "uuid")
            )
            UUID id
    ) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @Operation(summary = "Delete product by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable("id")
            @Parameter(
                    name = "id",
                    description = "Product UUID",
                    required = true,
                    example = "c9a5d520-2b49-4a6c-b9b3-7f0e2a2c5a8e",
                    schema = @Schema(type = "string", format = "uuid")
            )
            UUID id
    ) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all products by vendor")
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<Product>> getProductsByVendor(
            @PathVariable("vendorId")
            @Parameter(
                    name = "vendorId",
                    description = "Vendor UUID",
                    required = true,
                    example = "a1e8d5b0-15b4-4a5e-b6d3-8d60f7b9a123",
                    schema = @Schema(type = "string", format = "uuid")
            )
            UUID vendorId
    ) {
        return ResponseEntity.ok(productService.getProductsByVendor(vendorId));
    }

    @Operation(summary = "Get all products")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
