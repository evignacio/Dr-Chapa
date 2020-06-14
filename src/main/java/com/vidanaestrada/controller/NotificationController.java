package com.vidanaestrada.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class NotificationController {

    @RequestMapping("/notificacao")
    public SseEmitter notificaEvento() {
        return new SseEmitter();
    }
}
