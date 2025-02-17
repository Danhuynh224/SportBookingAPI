package com.example.apiproject.repository;

import com.example.apiproject.entity.Booking;
import com.example.apiproject.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookingDateBetween(LocalDate startDate, LocalDate endDate);
    List<Booking> findByStatus(BookingStatus status);
}
