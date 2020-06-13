package com.vidanaestrada.controller;

import com.vidanaestrada.application.TruckerService;
import com.vidanaestrada.dto.TruckerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class TruckerController {

    @Autowired
    private TruckerService truckerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody TruckerDTO truckerDTO) {
        truckerService.save(truckerDTO);
    }

}
