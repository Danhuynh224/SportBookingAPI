package com.example.apiproject.controller;

import com.example.apiproject.dto.BookingRequestDTO;
import com.example.apiproject.entity.Booking;
import com.example.apiproject.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBooking()
    {
        List<Booking> bookings = bookingService.getAllBookings();
        if (bookings.isEmpty()) {
            return ResponseEntity.noContent().build(); // Trả về 204 nếu danh sách rỗng
        }
        return ResponseEntity.ok(bookings); // Trả về 200 OK với danh sách dữ liệu
    }
    @PostMapping("/add")
    public ResponseEntity<Booking> addBooking(@RequestBody BookingRequestDTO bookingRequest)
    {
        Booking booking = bookingService.addBooking(bookingRequest);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);

    }
}
