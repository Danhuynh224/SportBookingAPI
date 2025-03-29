package com.example.apiproject.dto;

import com.example.apiproject.entity.FacilityType;
import com.example.apiproject.enums.FacilityStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class SubFacilityDTO {
    private Long subFacilityId;
    private String name;
    private Long facilityId;
    private FacilityType facilityType;
    private FacilityStatus status = FacilityStatus.AVAILABLE;
}
