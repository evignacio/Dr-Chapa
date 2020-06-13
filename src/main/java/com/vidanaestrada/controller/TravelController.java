package com.vidanaestrada.controller;

import com.vidanaestrada.application.TravelService;
import com.vidanaestrada.dto.TravelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/travel")
public class TravelController {

    @Autowired
    private TravelService travelService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<TravelDTO> generateTripPlanning(@RequestBody TravelDTO travelDTO, @RequestAttribute("truckerId") Long id) {
        travelDTO.setTruckerId(id);
        return new ResponseEntity<TravelDTO>(travelService.generateTripPlanning(travelDTO), HttpStatus.CREATED);
    }
}
