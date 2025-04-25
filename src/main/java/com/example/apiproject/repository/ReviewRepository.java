package com.example.apiproject.repository;

import com.example.apiproject.dto.ReviewRequest;
import com.example.apiproject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFacility_SportsFacilityId(Long facilityId);

    // Tính trung bình rating của một facility
    @Query("SELECT COALESCE(AVG(r.rating), 0) FROM Review r WHERE r.facility.sportsFacilityId = :facilityId")
    Double findAverageRatingByFacility(@Param("facilityId") Long facility);

    Review save(ReviewRequest reviewRequest);
}


