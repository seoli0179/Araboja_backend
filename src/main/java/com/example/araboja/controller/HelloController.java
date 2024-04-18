package com.example.araboja.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index(){
        return "Hello World!";
    }

    @GetMapping("/user")
    public String user(){
        return "유저권한 있음";
    }

}
