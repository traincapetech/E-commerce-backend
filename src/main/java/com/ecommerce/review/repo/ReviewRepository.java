package com.ecommerce.review.repo;

import com.ecommerce.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByProductId(UUID productId);
    List<Review> findByCustomerId(UUID customerId);
}
