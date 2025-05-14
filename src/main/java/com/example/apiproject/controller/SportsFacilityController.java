package com.example.apiproject.controller;

import com.example.apiproject.entity.SportsFacility;
import com.example.apiproject.service.SportsFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/sportsfacilities")
public class SportsFacilityController {
    @Autowired
    private SportsFacilityService sportsFacilityService;

    @PostMapping(value = "/save")
    public ResponseEntity<SportsFacility> save(@RequestBody SportsFacility sportsFacility) {
        SportsFacility savedFacility = sportsFacilityService.saveOrUpdateSportsFacility(sportsFacility);
        return ResponseEntity.ok(savedFacility);
    }

    // API lấy danh sách tất cả các sân
    @GetMapping
    public List<SportsFacility> getAllSportsFacilities() {
        List<SportsFacility> facilities = sportsFacilityService.getAllSportsFacility();
        if (facilities.isEmpty()) {
            return facilities;
        }
        return facilities;
    }

    // Lấy danh sách các sân ở gần
    @GetMapping("/nearby")
    public ResponseEntity<List<SportsFacility>> getSportFacilityNearMe(
            @RequestParam double latitude,
            @RequestParam double longitude) {

        List<SportsFacility> facilities = sportsFacilityService.findNearbyFacilities(latitude, longitude);
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

    @GetMapping("/search")
    public ResponseEntity<List<SportsFacility>> searchFacilities(@RequestParam String keyword) {
        List<SportsFacility> facilities = sportsFacilityService.searchFacilities(keyword);
        if (facilities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facilities);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<SportsFacility>> filterSportsFacilities(
            @RequestParam(required = false) List<String> types,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) int minRating) {

        List<SportsFacility> facilities = sportsFacilityService.filterFacilities(types, address, minRating);
        if (facilities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facilities);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SportsFacility> getSportsFacilityById(@PathVariable("id") Long id) {
        SportsFacility sportsFacility = sportsFacilityService.getSportsFacilityById(id);
        if (sportsFacility == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sportsFacility);
    }

}
