package com.githubrepositoryapp.githubrepository.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler({org.springframework.web.client.HttpClientErrorException.NotFound.class})
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleException(org.springframework.web.client.HttpClientErrorException.NotFound ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("status", String.valueOf(HttpStatus.NOT_FOUND.value()));
        errorResponse.put("message", "User not found on GitHub");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
