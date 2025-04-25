package com.example.apiproject.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReviewRequest {
    private Long reviewId;
    private Long userId;
    private Long facilityId;
    private int rating;
    private String comment;
    private LocalDate createdAt;
}
