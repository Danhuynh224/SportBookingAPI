package com.example.apiproject.service;

import com.example.apiproject.dto.BookingRequestDTO;
import com.example.apiproject.entity.*;
import com.example.apiproject.enums.BookingStatus;
import com.example.apiproject.repository.BookingRepository;
import com.example.apiproject.repository.SportsFacilityRepository;
import com.example.apiproject.repository.SubFacilityRepository;
import com.example.apiproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubFacilityRepository subFacilityRepository;

    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    public Booking addBooking(Booking booking) {
        Booking saveBooking =booking;
        List<BookingInfo> bookingInfos = booking.getBookingInfos();
        for (BookingInfo bookingInfo : bookingInfos) {
            bookingInfo.setBooking(saveBooking);
        }
        saveBooking.setBookingInfos(bookingInfos);
        return bookingRepository.save(saveBooking);
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUser_UserId(userId);
    }


    public List<Booking> getBookingsByDate(LocalDate startDate, LocalDate endDate) {
        return bookingRepository.findByBookingDateBetween(startDate, endDate);
    }
    public List<Booking> getBookingByBookingDate(LocalDate bookingDate, Long facilityTypeId, Long subFacilityId)
    {
        return bookingRepository.findByBookingDateAndBookingInfosSubFacilityFacilityTypeFacilityTypeIdAndBookingInfosSubFacilitySubFacilityId(bookingDate,facilityTypeId,subFacilityId);
    }

}
