package com.vidanaestrada.application.implementation;

import com.vidanaestrada.application.VehicleService;
import com.vidanaestrada.domain.entity.trucker.Trucker;
import com.vidanaestrada.domain.entity.vehicle.Vehicle;
import com.vidanaestrada.domain.repository.TruckerRepository;
import com.vidanaestrada.domain.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleServiceImp implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TruckerRepository truckerRepository;

    @Override
    public void save(Vehicle vehicle, Long truckerId) {

        Optional<Trucker>  truckerOpt = truckerRepository.findById(truckerId);
        if(!truckerOpt.isPresent())
            throw new RuntimeException("Trucker not found");

        vehicle.setTrucker(truckerOpt.get());
        vehicleRepository.save(vehicle);
    }
}
