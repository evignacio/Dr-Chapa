package com.vidanaestrada.domain.repository;

import com.vidanaestrada.domain.entity.travel.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Long> {
}
