package com.example.apiproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(nullable = false)
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(nullable = false)
    private LocalTime endTime;
    @Column(nullable = false)
    private BigDecimal totalPrice;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "booking_id")
    private Booking booking;

}
