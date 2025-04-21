package com.example.apiproject.entity;

import com.example.apiproject.enums.FacilityStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SubFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subFacilityId;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "sports_facility_id", nullable = false)
    private SportsFacility sportsFacility; // Thuộc một khu thể thao

    @ManyToOne
    @JoinColumn(name = "facility_type_id", nullable = false)
    private FacilityType facilityType; // Thuộc loại sân nào (Cầu lông, Tennis, ...)

    @JsonIgnore
    @OneToMany(mappedBy = "subFacility", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingInfo> bookingInfos = new ArrayList<>();




}
