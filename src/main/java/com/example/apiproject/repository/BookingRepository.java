package com.example.apiproject.repository;

import com.example.apiproject.entity.Booking;
import com.example.apiproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookingDateBetween(LocalDate startDate, LocalDate endDate);

    List<Booking> findByUser_UserId(Long userId);

    List<Booking> findByBookingDateAndBookingInfosSubFacilityFacilityTypeFacilityTypeIdAndBookingInfosSubFacilitySubFacilityId(
            @Param("bookingDate") LocalDate bookingDate,
            @Param("facilityTypeId") Long facilityTypeId,
            @Param("subFacilityId") Long subFacilityId);

    List<Booking> findBookingByBookingDate(LocalDate bookingDate);

}
