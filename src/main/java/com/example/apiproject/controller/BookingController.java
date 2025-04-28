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
//    @GetMapping("/filterByStatus")
//    public ResponseEntity<List<Booking>> filterBookingByDate(
//            @RequestParam String status) {
//        List<Booking> bookings = bookingService.getBookingsByStatus(status);
//        if (bookings.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(bookings);
//    }

    @PostMapping("/add")
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {
        System.out.println("Received booking: " + booking); // Log booking
        Booking savedBooking = bookingService.addBooking(booking);
        if(savedBooking == null) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    @GetMapping("/getByDateAndTypeAndSubFa")
    public ResponseEntity<List<Booking>> getBookingByDate(@RequestParam LocalDate bookingDate, @RequestParam Long facilityTypeId, @RequestParam Long subFacilityId) {
        List<Booking> bookings = bookingService.getBookingByBookingDate(bookingDate, facilityTypeId, subFacilityId);
        if (bookings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingByUserId(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getBookingsByUserId(userId);
        if (bookings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/{bookingId}/updateStatus")
    public ResponseEntity<Booking> updateBookingStatus(@PathVariable Long bookingId) {
        Booking updatedBooking = bookingService.updateBookingStatus(bookingId);
        if (updatedBooking != null) {
            return ResponseEntity.ok(updatedBooking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        boolean isDeleted = bookingService.deleteBooking(bookingId);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // 204 No Content response
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found if booking doesn't exist
        }
    }
}
