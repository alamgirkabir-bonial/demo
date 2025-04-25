package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    /**
     * This is a simple REST controller that provides two endpoints:
     * /hello and /goodbye.
     *
     * @return A greeting message.
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    /**
     * This endpoint returns a goodbye message.
     *
     * @return A goodbye message.
     */
    @GetMapping("/goodbye")
    public String goodbye() {
        return "Goodbye, World!";
    }
}
