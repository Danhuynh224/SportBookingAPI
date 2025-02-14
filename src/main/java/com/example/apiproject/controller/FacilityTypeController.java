package com.example.apiproject.controller;

import com.example.apiproject.dto.FacilityTypeDTO;
import com.example.apiproject.entity.FacilityType;
import com.example.apiproject.service.FacilityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/facilitytypes")
public class FacilityTypeController {
    @Autowired
    FacilityTypeService facilityTypeService;

    // Liệt kê các danh mục sân
    @GetMapping()
    public List<FacilityType> getFacilityTypes() {
        return facilityTypeService.getAllFacilityTypes();
    }
    // Liệt kê các sân theo loại sân
    @GetMapping("/facilitynames")
    public List<FacilityTypeDTO> getAllFacilityTypes() {
        return facilityTypeService.getAllFacilityTypes()
                .stream()
                .map(FacilityTypeDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
