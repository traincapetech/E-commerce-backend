package com.ecommerce.product.service;

import com.ecommerce.product.model.Product;
import com.ecommerce.product.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product addProduct(Product product) {
        product.setCreatedAt(Instant.now());
        product.setUpdatedAt(Instant.now());
        return productRepository.save(product);
    }

    public Product updateProduct(UUID id, Product updated) {
        Product existing = productRepository.findById(id).orElseThrow();
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setCategory(updated.getCategory());
        existing.setSaleDescription(updated.getSaleDescription());
        existing.setImages(updated.getImages());
        existing.setStockQuantity(updated.getStockQuantity());
        existing.setUpdatedAt(Instant.now());
        return productRepository.save(existing);
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    public Product getProduct(UUID id) {
        return productRepository.findById(id).orElseThrow();
    }

    public List<Product> getProductsByVendor(UUID vendorId) {
        return productRepository.findByVendorId(vendorId);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
