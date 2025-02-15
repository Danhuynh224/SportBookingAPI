package com.example.apiproject.service;

import com.example.apiproject.entity.SportsFacility;
import com.example.apiproject.repository.SportsFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class SportsFacilityService {
    @Autowired
    private SportsFacilityRepository sportsFacilityRepository;

    // Lấy danh sách các sân được xây dựng thêm các đây 7 ngày
    public List<SportsFacility> findAllRencentFacility() {
        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
        return sportsFacilityRepository.findAll().stream().
                filter(e->e.getCreatedAt().isAfter(sevenDaysAgo.atStartOfDay()) || e.getCreatedAt().isEqual(sevenDaysAgo.atStartOfDay()))
                .collect(Collectors.toList());
    }

    // Lấy danh sách 10 sân được đặt nhiều nhất
    public List<SportsFacility> findAllByOrderByBookingDes10() {
        return sportsFacilityRepository.findAllByOrderByBookingDes10();
    }


}
