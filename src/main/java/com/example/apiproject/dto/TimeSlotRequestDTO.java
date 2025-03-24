package com.example.apiproject.dto;

import com.example.apiproject.entity.FacilityType;
import com.example.apiproject.entity.SubFacility;
import com.example.apiproject.enums.TimeRange;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeSlotRequestDTO {
    private TimeRange timeRange;
    private BigDecimal price;
    private SubFacility subFacility;
    private FacilityType facilityType;
}
