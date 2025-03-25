package com.example.apiproject.repository;

import com.example.apiproject.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends JpaRepository<Price, Long> {
}
