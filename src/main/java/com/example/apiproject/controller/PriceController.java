package com.example.apiproject.controller;

import com.example.apiproject.dto.PriceDTO;
import com.example.apiproject.entity.Price;
import com.example.apiproject.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/price")
public class PriceController {
    @Autowired
    private PriceService priceService;

    @GetMapping()
    public ResponseEntity<Price> getPrice(@RequestParam Long faID, @RequestParam Long typeID) {
        Price price = priceService.getPrice(faID,typeID);
        if (price == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(price);
    }

    @PostMapping("/save")
    public ResponseEntity<Price> savePrice(@RequestBody PriceDTO priceDTO) {
        Price priceSave = priceService.savePrice(priceDTO);
        if (priceSave == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(priceSave);
    }
}
