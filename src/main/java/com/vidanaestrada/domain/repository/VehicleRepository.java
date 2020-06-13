package com.vidanaestrada.domain.repository;

import com.vidanaestrada.domain.entity.trucker.Trucker;
import com.vidanaestrada.domain.entity.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByTrucker(Trucker trucker);
}
