package com.example.apiproject.repository;

import com.example.apiproject.entity.FacilityType;
import com.example.apiproject.entity.SubFacility;
import com.example.apiproject.entity.TimeSlot;
import com.example.apiproject.enums.TimeRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
}
