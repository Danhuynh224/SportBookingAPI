package com.example.apiproject.repository;

import com.example.apiproject.entity.FacilityType;
import com.example.apiproject.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    Price findPriceByFacilitySportsFacilityIdAndFacilityTypeFacilityTypeId(Long facility_sportsFacilityId, Long facilityType_facilityTypeId);
}
