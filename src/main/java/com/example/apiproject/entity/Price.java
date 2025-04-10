package com.example.apiproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priceID;
    @Column(nullable = false)
    private BigDecimal earlyTime ;
    @Column(nullable = false)
    private BigDecimal dayTime ;
    @Column(nullable = false)
    private BigDecimal nightTime ;
    @Column(nullable = false)
    private BigDecimal weekTime ;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "facility_id", nullable = false)
    private SportsFacility facility;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private FacilityType facilityType;

}
