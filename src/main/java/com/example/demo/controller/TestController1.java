package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController1 {
    static int x = 10;
    static int y = 5;

    @GetMapping("/test1")
    public int hello() {
        return x + y;
    }

}
