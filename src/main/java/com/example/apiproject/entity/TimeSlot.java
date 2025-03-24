package com.example.apiproject.entity;

import com.example.apiproject.enums.TimeRange;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeSlotId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TimeRange timeRange;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "sub_facility_id", nullable = false)
    private SubFacility subFacility;

    @ManyToOne
    @JoinColumn(name = "facility_type_id", nullable = false)
    private FacilityType facilityType;
}
