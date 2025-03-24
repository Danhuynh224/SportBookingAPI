package com.example.apiproject.controller;

import com.example.apiproject.entity.SportsFacility;
import com.example.apiproject.entity.SubFacility;
import com.example.apiproject.service.SubFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subfacilities")
public class SubFacilityController {
    @Autowired
    SubFacilityService subFacilityService;

    @GetMapping()
    public ResponseEntity<List<SubFacility>> getAllSubFacilities() {
        List<SubFacility> subFacilities = subFacilityService.getAllSportsFacility();
        if (subFacilities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(subFacilities);
    }
}
