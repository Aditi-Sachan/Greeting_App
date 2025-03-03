package com.example.GreetingApp.Controller;

import org.springframework.web.bind.annotation.*;
import com.example.GreetingApp.Service.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

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

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public String getGreeting() {
        return greetingService.getGreeting();
    }

    @GetMapping
    public String getGreeting(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return greetingService.getGreeting(firstName, lastName);
    }

    // ✅ New Endpoint: Find Greeting by ID
    @GetMapping("/{id}")
    public String findGreetingById(@PathVariable Long id) {
        return greetingService.findGreetingById(id);
    }

    // ✅ New Endpoint: Get All Greetings
    @GetMapping("/all")
    public List<String> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    // ✅ New Endpoint: Update Greeting by ID
    @PutMapping("/{id}")
    public String updateGreeting(@PathVariable Long id, @RequestParam String newMessage) {
        return greetingService.updateGreeting(id, newMessage);
    }

    // ✅ New Endpoint: Delete Greeting by ID
    @DeleteMapping("/{id}")
    public String deleteGreeting(@PathVariable Long id) {
        return greetingService.deleteGreeting(id);
    }
}


