package com.example.apiproject.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingRequestDTO {
    private Long userId;
    private String facilityId;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private BigDecimal totalPrice;
}
