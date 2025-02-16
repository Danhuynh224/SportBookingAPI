package com.example.apiproject.repository;

import com.example.apiproject.entity.SportsFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportsFacilityRepository extends JpaRepository<SportsFacility, String> {

    @Query("SELECT b.facility FROM Booking b WHERE b.status='CONFIRMED' GROUP BY b.facility order by COUNT(b) DESC LIMIT 10")
    List<SportsFacility> findAllByOrderByBookingDes10();
}
