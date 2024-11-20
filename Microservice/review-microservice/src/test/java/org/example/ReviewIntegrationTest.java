package org.example;

import org.example.model.Review;
import org.example.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MongoDBContainer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReviewIntegrationTest {

    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @Autowired
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        mongoDBContainer.start();
        reviewRepository.deleteAll();
    }

    @Test
    void shouldCreateAndRetrieveReview() {
        Review review = new Review();
        review.setUsername("test_user");
        review.setTitle("Great Service");
        review.setContent("I had a wonderful experience!");
        review.setRating(5);
        reviewRepository.save(review);

        List<Review> reviews = reviewRepository.findAll();
        assertThat(reviews).hasSize(1);
        assertThat(reviews.get(0).getTitle()).isEqualTo("Great Service");
    }
}
