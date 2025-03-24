package com.example.apiproject.controller;

import com.example.apiproject.dto.TimeSlotRequestDTO;
import com.example.apiproject.entity.TimeSlot;
import com.example.apiproject.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timeslot")
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    @PostMapping("/create")
    public ResponseEntity<TimeSlot> createTimeSlot(@RequestBody TimeSlotRequestDTO request) {
        TimeSlot timeSlot = timeSlotService.createTimeSlot(request);
        return new ResponseEntity<>(timeSlot, HttpStatus.CREATED);
    }
}
