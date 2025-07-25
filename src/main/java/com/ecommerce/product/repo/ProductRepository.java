package com.ecommerce.product.repo;

import com.ecommerce.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByVendorId(UUID vendorId);
}
