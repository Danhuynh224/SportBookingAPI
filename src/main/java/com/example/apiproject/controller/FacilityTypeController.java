package com.example.apiproject.controller;

import com.example.apiproject.dto.FacilityTypeDTO;
import com.example.apiproject.entity.FacilityType;
import com.example.apiproject.service.FacilityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/facilitytypes")
public class FacilityTypeController {
    @Autowired
    private FacilityTypeService facilityTypeService;

    // API lấy danh sách loại sân
    @GetMapping
    public ResponseEntity<List<FacilityType>> getFacilityTypes() {
        List<FacilityType> facilityTypes = facilityTypeService.getAllFacilityTypes();
        if (facilityTypes.isEmpty()) {
            return ResponseEntity.noContent().build(); // Trả về 204 nếu danh sách rỗng
        }
        return ResponseEntity.ok(facilityTypes); // Trả về 200 OK với danh sách dữ liệu
    }

    // API lấy danh sách sân theo loại sân
    @GetMapping("/facilitynames")
    public ResponseEntity<List<FacilityTypeDTO>> getFacilityNames() {
        List<FacilityTypeDTO> facilityTypeDTOs = facilityTypeService.getAllFacilityTypes()
                .stream()
                .map(FacilityTypeDTO::fromEntity)
                .collect(Collectors.toList());

        if (facilityTypeDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facilityTypeDTOs);
    }
}
