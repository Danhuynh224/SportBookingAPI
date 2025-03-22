package com.example.apiproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;

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

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private BigDecimal pricePerHour;

    @ManyToOne
    @JoinColumn(name = "sports_facility_id", nullable = false)
    private SportsFacility sportsFacility;

    @ManyToOne
    @JoinColumn(name = "facility_type_id", nullable = false)
    private FacilityType facilityType;
}
