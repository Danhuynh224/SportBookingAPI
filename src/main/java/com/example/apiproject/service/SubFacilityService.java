package com.example.apiproject.service;

import com.example.apiproject.entity.SportsFacility;
import com.example.apiproject.entity.SubFacility;
import com.example.apiproject.repository.SubFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubFacilityService {
    @Autowired
    private SubFacilityRepository subFacilityRepository;
    public List<SubFacility> getAllSportsFacility() {
        return subFacilityRepository.findAll();
    }

    public SubFacility saveOrUpdateSubFacility(SubFacility subFacility) {
        return subFacilityRepository.save(subFacility);
    }
}
