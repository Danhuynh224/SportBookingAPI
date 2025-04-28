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
        if(checkCanBook(booking)) {
            return bookingRepository.save(saveBooking);
        }
        return null;
    }

    private boolean checkCanBook(Booking booking) {
        List<Booking> bookingChecks = bookingRepository.findBookingByBookingDate(booking.getBookingDate());

        List<BookingInfo> bookingInfos = booking.getBookingInfos();
        for(Booking bookingCheck : bookingChecks){
            List<BookingInfo> bookingInfosCheck = bookingCheck.getBookingInfos();
            for(BookingInfo bookingInfoCheck : bookingInfosCheck){
                for(BookingInfo bookingInfo :bookingInfos){
                    if(bookingInfo.getSubFacility().getSubFacilityId() == bookingInfoCheck.getSubFacility().getSubFacilityId()
                    && (bookingInfo.getStartTime().isAfter(bookingInfoCheck.getStartTime()) || bookingInfo.getStartTime().equals(bookingInfoCheck.getStartTime()))
                    && (bookingInfo.getEndTime().isBefore(bookingInfoCheck.getEndTime()) || bookingInfo.getEndTime().equals(bookingInfoCheck.getEndTime()))){
                        return false;
                    }
                }
            }
        }
        return true;
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

    public Booking updateBookingStatus(Long bookingId) {
        // Fetch the booking by ID
        Booking booking = bookingRepository.findById(bookingId).orElse(null);

        if (booking != null) {
            // Update the status of the booking
            booking.setStatus(BookingStatus.COMPLETED);
            return bookingRepository.save(booking);
        }
        return null; // Return null if the booking is not found
    }


    public boolean deleteBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            bookingRepository.delete(booking);
            return true; // Deletion was successful
        }
        return false; // Booking not found
    }
}
