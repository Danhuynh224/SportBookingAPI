package com.example.apiproject.controller;

import com.example.apiproject.entity.Review;
import com.example.apiproject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/facility/{facilityId}")
    public ResponseEntity<List<Review>> getReviewsByFacility(@PathVariable Long facilityId) {
        List<Review> reviews = reviewService.getReviewsByFacility(facilityId);
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reviews);
    }

    // lấy trung bình rating của một facility
    @GetMapping("/facility/{facilityId}/avg-rating")
    public ResponseEntity<Double> getAverageRatingByFacility(@PathVariable Long facilityId) {
        Double averageRating = reviewService.getAverageRatingByFacility(facilityId);
        return ResponseEntity.ok(averageRating);
    }
}
