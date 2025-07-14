package com.ecommerce.review.controller;

import com.ecommerce.review.model.Review;
import com.ecommerce.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "Add a review for a product")
    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.addReview(review));
    }

    @Operation(summary = "Get all reviews for a product")
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProduct(
            @PathVariable("productId")
            @Parameter(name = "productId", description = "Product UUID", required = true, example = "f5b3c764-6bb7-4c58-b04b-6e7a1a1c6cf2",
                    schema = @Schema(type = "string", format = "uuid"))
            UUID productId
    ) {
        return ResponseEntity.ok(reviewService.getReviewsByProduct(productId));
    }

    @Operation(summary = "Get all reviews by a customer")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Review>> getReviewsByCustomer(
            @PathVariable("customerId")
            @Parameter(name = "customerId", description = "Customer UUID", required = true, example = "c0e4ef48-e92a-403c-93e3-3a4d17e7a13c",
                    schema = @Schema(type = "string", format = "uuid"))
            UUID customerId
    ) {
        return ResponseEntity.ok(reviewService.getReviewsByCustomer(customerId));
    }

    @Operation(summary = "Delete a review by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable("id")
            @Parameter(name = "id", description = "Review UUID", required = true, example = "71ddae07-d8b4-43a8-802d-8880dc63e705",
                    schema = @Schema(type = "string", format = "uuid"))
            UUID id
    ) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
