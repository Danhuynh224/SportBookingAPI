package com.example.apiproject.repository;

import com.example.apiproject.entity.Price;
import com.example.apiproject.enums.TimeRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimeSlotRepository extends JpaRepository<Price, Long> {
}
