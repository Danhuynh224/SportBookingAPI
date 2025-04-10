package com.example.apiproject.service;

import com.example.apiproject.dto.PriceDTO;
import com.example.apiproject.entity.Price;
import com.example.apiproject.repository.FacilityTypeRepository;
import com.example.apiproject.repository.PriceRepository;
import com.example.apiproject.repository.SportsFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private FacilityTypeRepository facilityTypeRepository;
    @Autowired
    private SportsFacilityRepository sportsFacilityRepository;

    public Price getPrice(Long faID, Long typID) {
        return priceRepository.findPriceByFacilitySportsFacilityIdAndFacilityTypeFacilityTypeId(faID,typID);
    }

    public Price savePrice(PriceDTO priceDTO) {
        Price price = new Price();
        price.setDayTime(priceDTO.getDayTime());
        price.setEarlyTime(priceDTO.getEarlyTime());
        price.setNightTime(priceDTO.getNightTime());
        price.setWeekTime(priceDTO.getWeekTime());
        price.setFacilityType(facilityTypeRepository.findFacilityTypeByFacilityTypeId(priceDTO.getTypeID()));
        price.setFacility(sportsFacilityRepository.findBySportsFacilityId(priceDTO.getFaId()));
        priceRepository.save(price);
        return price;
    }
}
