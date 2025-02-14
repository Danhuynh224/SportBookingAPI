package com.example.apiproject.service;

import com.example.apiproject.entity.FacilityType;
import com.example.apiproject.repository.FacilityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityTypeService {
    @Autowired
    FacilityTypeRepository facilityTypeRepository;

    public List<FacilityType> getAllFacilityTypes() {
        return facilityTypeRepository.findAll();
    }
}
