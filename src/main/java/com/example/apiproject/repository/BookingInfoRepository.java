package com.example.apiproject.repository;

import com.example.apiproject.entity.BookingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingInfoRepository extends JpaRepository<BookingInfo, Long> {
}
