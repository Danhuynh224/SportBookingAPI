package com.example.apiproject.service;

import com.example.apiproject.dto.BookingRequestDTO;
import com.example.apiproject.entity.Booking;
import com.example.apiproject.entity.SportsFacility;
import com.example.apiproject.entity.SubFacility;
import com.example.apiproject.entity.User;
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

    public Booking addBooking(BookingRequestDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        SubFacility facility = subFacilityRepository.findById(request.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Facility not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setSubFacility(facility);
        booking.setBookingDate(request.getBookingDate());
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setTotalPrice(request.getTotalPrice());
        booking.setStatus(BookingStatus.PENDING);

        return bookingRepository.save(booking);
    }

    public boolean cancelBooking(Long id) {
        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            // Chỉ hủy trước thời gian đặt sân ít nhất 1 giờ
            LocalDateTime bookingDateTime = booking.getBookingDate().atTime(booking.getStartTime());

            if (bookingDateTime.isAfter(LocalDateTime.now().plusHours(1))) {
                booking.setStatus(BookingStatus.CANCELLED);
                bookingRepository.save(booking);
                return true;
            }
        }
        return false;
    }

    public List<Booking> getBookingsByDate(LocalDate startDate, LocalDate endDate) {
        return bookingRepository.findByBookingDateBetween(startDate, endDate);
    }

    public List<Booking> getBookingsByStatus(String status) {
        return bookingRepository.findByStatus(BookingStatus.valueOf(status.toUpperCase()));
    }
}
