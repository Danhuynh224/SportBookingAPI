package com.example.apiproject.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceDTO {
    private BigDecimal earlyTime ;
    private BigDecimal dayTime ;
    private BigDecimal nightTime ;
    private BigDecimal weekTime ;
    private Long faId;
    private Long typeID;
}
