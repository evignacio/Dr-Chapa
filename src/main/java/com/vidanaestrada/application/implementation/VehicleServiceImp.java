package com.vidanaestrada.application.implementation;

import com.vidanaestrada.application.VehicleService;
import com.vidanaestrada.domain.entity.vehicle.Vehicle;
import com.vidanaestrada.domain.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImp implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }
}
