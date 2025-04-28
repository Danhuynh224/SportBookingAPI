package com.example.apiproject.service;

import com.example.apiproject.entity.SportsFacility;
import com.example.apiproject.entity.SubFacility;
import com.example.apiproject.repository.SportsFacilityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    public SportsFacility getSportsFacilityById(Long id) {
        return sportsFacilityRepository.findBySportsFacilityId(id);
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


    public List<SportsFacility> filterFacilities(List<String> types, String address, BigDecimal minPrice, BigDecimal maxPrice) {
        List<SportsFacility> filteredFacilities = sportsFacilityRepository.findAll().stream()
                .filter(facility -> (types == null || facility.getPrices().stream().anyMatch(price -> types.contains(price.getFacilityType().getName()))))
                .filter(facility -> (address == null || facility.getAddress().toLowerCase().contains(address.toLowerCase())))
                .filter(facility -> (minPrice == null || facility.getPrices().stream().anyMatch(price -> price.getDayTime().compareTo(minPrice) >= 0)))
                .filter(facility -> (maxPrice == null || facility.getPrices().stream().anyMatch(price -> price.getDayTime().compareTo(maxPrice) <= 0)))
                .toList();

        return filteredFacilities;
    }


    public List<SportsFacility> findNearbyFacilities(double userLatitude, double userLongitude) {
        List<SportsFacility> facilities = getAllSportsFacility(); // gọi hàm có sẵn để lấy tất cả sân

        return facilities.stream()
                .filter(facility -> facility.getLatitude() != 0 && facility.getLongitude() != 0)
                .sorted((f1, f2) -> {
                    double distance1 = calculateDistance(userLatitude, userLongitude, f1.getLatitude(), f1.getLongitude());
                    double distance2 = calculateDistance(userLatitude, userLongitude, f2.getLatitude(), f2.getLongitude());
                    return Double.compare(distance1, distance2); // Sắp xếp gần → xa
                })
                .collect(Collectors.toList());
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371; // Bán kính Trái Đất (km)

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // Khoảng cách tính bằng km
    }



}
