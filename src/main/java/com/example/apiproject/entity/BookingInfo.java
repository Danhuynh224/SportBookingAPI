package com.example.apiproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Data
public class BookingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingInfoId;

    @ManyToOne
    @JoinColumn(name = "sub_facility_id", nullable = false)
    private SubFacility subFacility;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
