package com.vidanaestrada.controller;

import com.vidanaestrada.application.TravelService;
import com.vidanaestrada.dto.TravelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/travel")
public class TravelController {

    @Autowired
    private TravelService travelService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody TravelDTO travelDTO, @RequestAttribute("truckerId") Long id) {
        travelDTO.setTrackerId(id);
        travelService.save(travelDTO);
    }
}
