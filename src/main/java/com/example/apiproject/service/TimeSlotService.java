package com.example.apiproject.service;

import com.example.apiproject.dto.TimeSlotRequestDTO;
import com.example.apiproject.entity.FacilityType;
import com.example.apiproject.entity.SubFacility;
import com.example.apiproject.entity.TimeSlot;
import com.example.apiproject.repository.FacilityTypeRepository;
import com.example.apiproject.repository.SubFacilityRepository;
import com.example.apiproject.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeSlotService {
    @Autowired
    private TimeSlotRepository timeSlotRepository;

    public TimeSlot createTimeSlot(TimeSlotRequestDTO request) {
        if (request.getSubFacility() == null || request.getFacilityType() == null) {
            throw new RuntimeException("Không tìm thấy loại sân và tên sân");
        }

        TimeSlot timeSlot = TimeSlot.builder()
                .timeRange(request.getTimeRange())
                .price(request.getPrice())
                .subFacility(request.getSubFacility())
                .facilityType(request.getFacilityType())
                .build();

        return timeSlotRepository.save(timeSlot);
    }
}
