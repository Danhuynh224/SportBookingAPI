package com.example.apiproject.controller;

import com.example.apiproject.entity.SportsFacility;
import com.example.apiproject.service.SportsFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sportsfacilities")
public class SportsFacilityController {
    @Autowired
    private SportsFacilityService sportsFacilityService;

    @PostMapping("/save")
    public ResponseEntity<SportsFacility> save(@RequestBody SportsFacility sportsFacility) {
        SportsFacility savedFacility = sportsFacilityService.saveOrUpdateSportsFacility(sportsFacility);
        return ResponseEntity.ok(savedFacility);
    }

    // API lấy danh sách tất cả các sân
    @GetMapping
    public ResponseEntity<List<SportsFacility>> getAllSportsFacilities() {
        List<SportsFacility> facilities = sportsFacilityService.getAllSportsFacility();
        if (facilities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facilities);
    }

    // API lấy danh sách sân xây 7 ngày trước
    @GetMapping("/recent")
    public ResponseEntity<List<SportsFacility>> getRecentFacility() {
        List<SportsFacility> facilities = sportsFacilityService.findAllRecentFacility();
        if (facilities.isEmpty()) {
            return ResponseEntity.noContent().build(); // Trả về 204 NO CONTENT nếu danh sách rỗng
        }
        return ResponseEntity.ok(facilities); // Trả về danh sách với status 200 OK
    }

    // API lấy ra 10 sân được booking nhiều nhất
    @GetMapping("/top10")
    public ResponseEntity<List<SportsFacility>> getTop10Facility() {
        List<SportsFacility> facilities = sportsFacilityService.findAllByOrderByBookingDesc10();
        if (facilities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facilities);
    }
}
