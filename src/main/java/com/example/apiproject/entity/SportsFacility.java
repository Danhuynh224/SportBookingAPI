package com.example.apiproject.entity;

import com.example.apiproject.enums.FacilityStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SportsFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sportsFacilityId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String address;

    private double latitude;
    private double longitude;
    private String img;

    @JsonIgnore
    @OneToMany(mappedBy = "sportsFacility", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubFacility> subFacilities = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Price> prices = new ArrayList<>();
}
