package com.example.GreetingApp.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping
    public String getGreeting() {
        return "{\"message\":\"Hello, this is a GET request!\"}";
    }

    @PostMapping
    public String postGreeting(@RequestBody String request) {
        return "{\"message\":\"Hello, this is a POST request!\", \"received\":\"" + request + "\"}";
    }

    @PutMapping
    public String putGreeting(@RequestBody String request) {
        return "{\"message\":\"Hello, this is a PUT request!\", \"updated\":\"" + request + "\"}";
    }

    @DeleteMapping
    public String deleteGreeting() {
        return "{\"message\":\"Hello, this is a DELETE request!\"}";
    }
}


