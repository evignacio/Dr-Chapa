package com.vidanaestrada.application;

import com.vidanaestrada.domain.entity.vehicle.Vehicle;

public interface VehicleService {
    void save(Vehicle vehicle, Long truckerId);
}
