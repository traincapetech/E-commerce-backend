package com.ecommerce.review.service;

import com.ecommerce.review.model.Review;
import com.ecommerce.review.repo.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review addReview(Review review) {
        review.setCreatedAt(Instant.now());
        review.setUpdatedAt(Instant.now());
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByProduct(UUID productId) {
        return reviewRepository.findByProductId(productId);
    }

    public List<Review> getReviewsByCustomer(UUID customerId) {
        return reviewRepository.findByCustomerId(customerId);
    }

    public void deleteReview(UUID reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
