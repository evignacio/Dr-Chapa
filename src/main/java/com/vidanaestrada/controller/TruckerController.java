package com.vidanaestrada.controller;

import com.vidanaestrada.application.UserService;
import com.vidanaestrada.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
    }

}
