package com.example.apiproject.service;

import com.example.apiproject.entity.Review;
import com.example.apiproject.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByFacility(Long facilityId) {
        return reviewRepository.findByFacility_SportsFacilityId(facilityId);
    }

    public Double getAverageRatingByFacility(Long facilityId) {
        return reviewRepository.findAverageRatingByFacility(facilityId);
    }


}
