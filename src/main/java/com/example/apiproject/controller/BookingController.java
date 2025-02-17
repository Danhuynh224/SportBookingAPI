package com.example.apiproject.controller;

import com.example.apiproject.dto.BookingRequestDTO;
import com.example.apiproject.entity.Booking;
import com.example.apiproject.enums.BookingStatus;
import com.example.apiproject.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

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

    // test /filterByDate?startDate=2025-02-10&endDate=2025-02-10
    @GetMapping("/filterByDate")
    public ResponseEntity<List<Booking>> filterBookingByDate(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<Booking> bookings = bookingService.getBookingsByDate(startDate, endDate);
        if (bookings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);
    }

    // test /filterByStatus?status=CONFIRMED
    @GetMapping("/filterByStatus")
    public ResponseEntity<List<Booking>> filterBookingByDate(
            @RequestParam String status) {
        List<Booking> bookings = bookingService.getBookingsByStatus(status);
        if (bookings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);
    }

    @PostMapping("/add")
    public ResponseEntity<Booking> addBooking(@RequestBody BookingRequestDTO bookingRequest)
    {
        Booking booking = bookingService.addBooking(bookingRequest);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id)
    {
        boolean isCanceled = bookingService.cancelBooking(id);
        if (isCanceled) {
            return ResponseEntity.ok("Đã hủy thành công.");
        }
        return ResponseEntity.badRequest().body("Không thể hủy. Cần hủy trước ít nhất 24 giờ!");
    }
}
