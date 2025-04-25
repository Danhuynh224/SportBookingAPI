package com.example.apiproject.service;

import com.example.apiproject.dto.ReviewRequest;
import com.example.apiproject.entity.Review;
import com.example.apiproject.entity.SportsFacility;
import com.example.apiproject.entity.User;
import com.example.apiproject.repository.ReviewRepository;
import com.example.apiproject.repository.SportsFacilityRepository;
import com.example.apiproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SportsFacilityRepository sportsFacilityRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByFacility(Long facilityId) {
        return reviewRepository.findByFacility_SportsFacilityId(facilityId);
    }

    public Double getAverageRatingByFacility(Long facilityId) {
        return reviewRepository.findAverageRatingByFacility(facilityId);
    }

    public Review saveReview(ReviewRequest request) {
        User user = userRepository.findByUserId(request.getUserId());

        SportsFacility sportsFacility = sportsFacilityRepository.findBySportsFacilityId(request.getFacilityId());

        Review review = new Review();
        review.setUser(user);
        review.setFacility(sportsFacility);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        return reviewRepository.save(review);
    }

}
