package org.example.service;

import org.example.client.ProductClient;
import org.example.model.Product;
import org.example.model.Review;
import org.example.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductClient productClient;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewWithProductDetails(String reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with ID: " + reviewId));

        Product product = productClient.getProductById(review.getProductId());
        review.setProduct(product);
        return review;
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public void deleteReview(String reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}

