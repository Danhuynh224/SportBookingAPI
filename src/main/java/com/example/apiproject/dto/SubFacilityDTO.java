package com.example.apiproject.dto;

import com.example.apiproject.entity.FacilityType;
import com.example.apiproject.enums.FacilityStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SubFacilityDTO {
    private Long subFacilityId;
    private String name;
    private Long facilityId;
    private FacilityType facilityType;
//    private FacilityStatus status = FacilityStatus.AVAILABLE;
}
