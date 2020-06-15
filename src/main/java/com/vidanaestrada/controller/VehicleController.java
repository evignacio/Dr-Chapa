package com.vidanaestrada.controller;

import com.vidanaestrada.application.VehicleService;
import com.vidanaestrada.domain.entity.vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody Vehicle vehicle,  @RequestAttribute("truckerId") Long truckerId) {
        vehicleService.save(vehicle, truckerId);
    }
}
