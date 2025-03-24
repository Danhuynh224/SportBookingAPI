package com.example.apiproject.service;

import com.example.apiproject.entity.SportsFacility;
import com.example.apiproject.repository.SportsFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class SportsFacilityService {
    @Autowired
    private SportsFacilityRepository sportsFacilityRepository;

    // Lấy danh sách tất cả các sân
    public List<SportsFacility> getAllSportsFacility() {
        return sportsFacilityRepository.findAll();
    }

    // Lấy danh sách các sân được xây dựng thêm các đây 7 ngày
    public List<SportsFacility> findAllRecentFacility() {
        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
        return sportsFacilityRepository.findAll().stream().
                filter(e->e.getCreatedAt().isAfter(sevenDaysAgo.atStartOfDay()) || e.getCreatedAt().isEqual(sevenDaysAgo.atStartOfDay()))
                .collect(Collectors.toList());
    }

    // Lấy danh sách 10 sân được đặt nhiều nhất
//    public List<SportsFacility> findAllByOrderByBookingDesc10() {
//        return sportsFacilityRepository.findAllByOrderByBookingDes10();
//    }

    // Cập nhật hoặc thêm mới một sân thể thao
    public SportsFacility saveOrUpdateSportsFacility(SportsFacility facility) {
        return sportsFacilityRepository.save(facility);
    }

    public List<SportsFacility> searchFacilities(String keyword) {
        Set<SportsFacility> uniqueFacilities = new HashSet<>();
        uniqueFacilities.addAll(sportsFacilityRepository.findByNameContainingIgnoreCase(keyword));
        uniqueFacilities.addAll(sportsFacilityRepository.findByAddressContainingIgnoreCase(keyword));

        if (uniqueFacilities.isEmpty()) {
            throw new RuntimeException("No facilities found");
        }
        return new ArrayList<>(uniqueFacilities);
    }
}
