package com.vidanaestrada.controller;

import com.vidanaestrada.core.security.AccountCredential;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SignatureException;

@RestController
@RequestMapping("/api/v1/auth")
public class AutenticationController {

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<String> login(@RequestBody AccountCredential accountCredential) {
        return new ResponseEntity<String>("Login efetuado com sucesso", HttpStatus.OK);
    }
}
