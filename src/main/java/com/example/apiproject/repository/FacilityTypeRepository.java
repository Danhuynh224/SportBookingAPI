package com.example.apiproject.repository;

import com.example.apiproject.entity.FacilityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityTypeRepository extends JpaRepository<FacilityType, Long> {
    FacilityType findFacilityTypeByFacilityTypeId(long id);
}
