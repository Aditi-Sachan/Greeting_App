package com.example.GreetingApp.Service;

import com.example.GreetingApp.Model.Greeting;
import com.example.GreetingApp.Repository.GreetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    // ✅ Method 1: Default Greeting
    public String getGreeting() {
        return "Hello World";
    }

    // ✅ Method 2: Personalized Greeting (Fix: Removed duplicate method)
    public String getGreeting(String firstName, String lastName) {
        String message;
        if (firstName != null && lastName != null) {
            message = "Hello, " + firstName + " " + lastName + "!";
        } else if (firstName != null) {
            message = "Hello, " + firstName + "!";
        } else if (lastName != null) {
            message = "Hello, " + lastName + "!";
        } else {
            message = "Hello World!";
        }

        // ✅ Save the greeting to the database
        Greeting greeting = new Greeting(message);
        greeting = greetingRepository.save(greeting);

        return "Saved Greeting ID: " + greeting.getId() + ", Message: " + greeting.getMessage();
    }

    // ✅ Method 3: Find Greeting by ID (UC5)
    public String findGreetingById(Long id) {
        Optional<Greeting> greeting = greetingRepository.findById(id);
        return greeting.map(Greeting::getMessage)
                .orElse("Greeting not found for ID: " + id);
    }

    // ✅ Method 4: Fetch All Greetings (UC6)
    public List<String> getAllGreetings() {
        List<Greeting> greetings = greetingRepository.findAll();
        return greetings.stream()
                .map(greeting -> "ID: " + greeting.getId() + ", Message: " + greeting.getMessage())
                .collect(Collectors.toList());
    }

    // ✅ Method 5: Update Greeting by ID (UC7)
    public String updateGreeting(Long id, String newMessage) {
        Optional<Greeting> optionalGreeting = greetingRepository.findById(id);
        if (optionalGreeting.isPresent()) {
            Greeting greeting = optionalGreeting.get();
            greeting.setMessage(newMessage);
            greetingRepository.save(greeting);
            return "Updated Greeting ID: " + id + " to: " + newMessage;
        } else {
            return "Greeting not found for ID: " + id;
        }
    }

    // ✅ Method 6: Delete Greeting by ID (UC8)
    public String deleteGreeting(Long id) {
        if (greetingRepository.existsById(id)) {
            greetingRepository.deleteById(id);
            return "Deleted Greeting ID: " + id;
        } else {
            return "Greeting not found for ID: " + id;
        }
    }
}

