package com.example.araboja.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index(){
        return "Hello World!";
    }

    @RequestMapping("/test200")
    public ResponseEntity<String> test200() {
        return ResponseEntity.ok().body("200");
    }

}
