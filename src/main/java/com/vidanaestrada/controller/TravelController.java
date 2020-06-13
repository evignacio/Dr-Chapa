package com.vidanaestrada.controller;

import com.vidanaestrada.application.TravelService;
import com.vidanaestrada.domain.entity.travel.Travel;
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
    public void save(@RequestBody Travel travel) {
        travelService.save(travel);
    }
}
