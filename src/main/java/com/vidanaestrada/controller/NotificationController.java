package com.vidanaestrada.controller;

import com.vidanaestrada.application.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    public SseEmitter notificaEvento() {
        return new SseEmitter();
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void register(@RequestHeader("notificationToken") String token) {
        this.notificationService.addToken(token);
    }

    @PostMapping("unregister")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unregister(@RequestParam("notificationToken") String token) {
        this.notificationService.removeToken(token);
    }
}
