package com.vidanaestrada.controller;

import com.vidanaestrada.application.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class Notification {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void register(@RequestParam("token") String token) {
        System.out.println("register: " + token);
        this.notificationService.addToken(token);
    }

    @PostMapping("/unregister")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unregister(@RequestParam("token") String token) {
        System.out.println("unregister: " + token);
        this.notificationService.removeToken(token);
    }
}
