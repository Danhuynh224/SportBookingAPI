package com.example.apiproject.service;

import com.example.apiproject.entity.Price;
import com.example.apiproject.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;
    public Price getPrice(Long faID, Long typID) {
        return priceRepository.findPriceByFacilitySportsFacilityIdAndFacilityTypeFacilityTypeId(faID,typID);
    }
}
