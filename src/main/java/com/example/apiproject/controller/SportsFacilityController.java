package com.example.apiproject.controller;

import com.example.apiproject.entity.SportsFacility;
import com.example.apiproject.service.SportsFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sprotfacilitis")
public class SportsFacilityController {
    @Autowired
    private SportsFacilityService sportsFacilityService;

    //Api lấy danh sách sân xây 7 ngày trước
    @GetMapping("/recent")
    public List<SportsFacility> getRecnetFacility() {
        return sportsFacilityService.findAllRencentFacility();
    }

    //API lấy ra 10 sân được bookinh nhiều nhất
    @GetMapping("/top10")
    public List<SportsFacility> getTop10Facility() {
        return sportsFacilityService.findAllByOrderByBookingDes10();
    }
}
