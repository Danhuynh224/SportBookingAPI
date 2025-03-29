package com.example.apiproject.repository;

import com.example.apiproject.entity.SubFacility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubFacilityRepository extends JpaRepository<SubFacility, Long> {
    SubFacility findBySubFacilityId(Long subFacilityId);
    List<SubFacility> findSubFacilitiesBySportsFacilitySportsFacilityId(Long facilityId);
}
