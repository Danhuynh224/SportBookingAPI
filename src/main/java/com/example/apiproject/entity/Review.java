package com.example.apiproject.entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false)
    private SportsFacility facility;

    @Column(nullable = false)
    private int rating; // 1 - 5 stars

    @Column(columnDefinition = "TEXT")
    private String comment;

}
