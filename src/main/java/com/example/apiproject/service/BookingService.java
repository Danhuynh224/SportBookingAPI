package com.example.apiproject.service;

import com.example.apiproject.dto.BookingRequestDTO;
import com.example.apiproject.entity.Booking;
import com.example.apiproject.entity.SportsFacility;
import com.example.apiproject.entity.User;
import com.example.apiproject.enums.BookingStatus;
import com.example.apiproject.repository.BookingRepository;
import com.example.apiproject.repository.SportsFacilityRepository;
import com.example.apiproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private SportsFacilityRepository sportsFacilityRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    public Booking addBooking(BookingRequestDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        SportsFacility facility = sportsFacilityRepository.findById(request.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Facility not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFacility(facility);
        booking.setBookingDate(request.getBookingDate());
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setTotalPrice(request.getTotalPrice());
        booking.setStatus(BookingStatus.PENDING);

        return bookingRepository.save(booking);
    }
}
