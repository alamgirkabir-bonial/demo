package com.example.demo.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api1/greet")
@Slf4j
public class TestController2 {


    @PostMapping
    public ResponseEntity<Map<String, Object>> greetUser(@RequestBody GreetingRequest request) {
        Map<String, Object> response = new HashMap<>();
        log.info("Received greeting request: {}", request);

        if (request.getName() == null || request.getName().isBlank()) {
            log.warn("Invalid name received");
            response.put("status", "error");
            response.put("message", "Name cannot be empty");
            return ResponseEntity.badRequest().body(response);
        }

        String name = request.getName().trim();
        String language = request.getLanguage() != null ? request.getLanguage().toLowerCase() : "en";
        log.debug("Processing name: {} with language: {}", name, language);

        String greeting;
        switch (language) {
            case "es":
                greeting = "¡Hola, " + name + "!";
                break;
            case "fr":
                greeting = "Bonjour, " + name + "!";
                break;
            case "de":
                greeting = "Hallo, " + name + "!";
                break;
            case "it":
                greeting = "Ciao, " + name + "!";
                break;
            case "en":
            default:
                greeting = "Hello, " + name + "!";
        }

        log.info("Greeting generated: {}", greeting);

        response.put("greeting", greeting);
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("uppercaseName", name.toUpperCase());
        response.put("nameLength", name.length());
        response.put("isPalindrome", isPalindrome(name));
        response.put("language", language);
        response.put("status", "success");

        List<String> randomFacts = new ArrayList<>();
        randomFacts.add("Did you know? Your name starts with: " + name.charAt(0));
        randomFacts.add("Your name has " + name.length() + " characters.");
        randomFacts.add("Reversed, your name is: " + new StringBuilder(name).reverse());

        response.put("funFacts", randomFacts);

        log.debug("Sending response: {}", response);
        return ResponseEntity.ok(response);
    }

    private boolean isPalindrome(String text) {
        String clean = text.replaceAll("[\\W_]", "").toLowerCase();
        return new StringBuilder(clean).reverse().toString().equals(clean);
    }

    @Data
    static class GreetingRequest {
        private String name;
        private String language;
    }
}
