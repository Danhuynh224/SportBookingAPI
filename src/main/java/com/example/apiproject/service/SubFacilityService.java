package com.example.apiproject.service;

import com.example.apiproject.dto.SubFacilityDTO;
import com.example.apiproject.entity.SportsFacility;
import com.example.apiproject.entity.SubFacility;
import com.example.apiproject.repository.SubFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubFacilityService {
    @Autowired
    private SubFacilityRepository subFacilityRepository;
    public List<SubFacilityDTO> getAllSportsFacility() {
        List<SubFacility> subFacilities = subFacilityRepository.findAll();
        List<SubFacilityDTO> subFacilityDTOs = new ArrayList<>();
        int size = subFacilities.size();
        SubFacility subFacility;
        SubFacilityDTO subFacilityDTO;
        for (int i = 0; i < size; i++) {
            subFacility = subFacilities.get(i);
            subFacilityDTO = new SubFacilityDTO();
            subFacilityDTO.setSubFacilityId(subFacility.getSubFacilityId());
            subFacilityDTO.setName(subFacility.getName());
            subFacilityDTO.setFacilityId(subFacility.getSportsFacility().getSportsFacilityId());
            subFacilityDTO.setStatus(subFacility.getStatus());
            subFacilityDTO.setFacilityType(subFacility.getFacilityType());
            subFacilityDTOs.add(subFacilityDTO);
        }
        return subFacilityDTOs;
    }

    public SubFacility saveOrUpdateSubFacility(SubFacility subFacility) {
        return subFacilityRepository.save(subFacility);
    }

    public List<SubFacilityDTO> getSubFacilitiesByFaID(Long faID) {
        List<SubFacility> subFacilities = subFacilityRepository.findSubFacilitiesBySportsFacilitySportsFacilityId(faID);
        List<SubFacilityDTO> subFacilityDTOs = new ArrayList<>();
        int size = subFacilities.size();
        SubFacility subFacility;
        SubFacilityDTO subFacilityDTO;
        for (int i = 0; i < size; i++) {
            subFacility = subFacilities.get(i);
            subFacilityDTO = new SubFacilityDTO();
            subFacilityDTO.setSubFacilityId(subFacility.getSubFacilityId());
            subFacilityDTO.setName(subFacility.getName());
            subFacilityDTO.setFacilityId(subFacility.getSportsFacility().getSportsFacilityId());
            subFacilityDTO.setStatus(subFacility.getStatus());
            subFacilityDTO.setFacilityType(subFacility.getFacilityType());
            subFacilityDTOs.add(subFacilityDTO);
        }
        return subFacilityDTOs;
    }
}
