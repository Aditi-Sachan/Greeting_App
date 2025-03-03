package com.example.GreetingApp.Service;

import com.example.GreetingApp.Model.Greeting;
import com.example.GreetingApp.Repository.GreetingRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GreetingService {


    public String getGreeting() {
        return "Hello World";
    }


    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public String getGreeting(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return "Hello, " + firstName + " " + lastName + "!";
        } else if (firstName != null) {
            return "Hello, " + firstName + "!";
        } else if (lastName != null) {
            return "Hello, " + lastName + "!";
        } else {
            return "Hello World!";
        }
    }

    final GreetingRepository greetingRepository = null;

        // Constructor-based injection (Spring will inject repository)
    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public String getGreeting (String firstName, String lastName) {
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
    }

    // ✅ Fix: Use 'new Greeting()' and ensure repository is injected
    Greeting greeting = new Greeting(message);
    greetingRepository.save(greeting);  // Save to database
    return message;

    //UC5
    // Save greeting to the database
    Greeting greeting = new Greeting(message);
    greeting = greetingRepository.save(greeting);
    return "Saved Greeting ID: " + greeting.getId() + ", Message: " + greeting.getMessage();


    // ✅ New Method: Find Greeting by ID
    public String findGreetingById(Long id) {
        Optional<Greeting> greeting = greetingRepository.findById(id);
        return greeting.map(Greeting::getMessage)
                .orElse("Greeting not found for ID: " + id);
    }

    //UC6
    // ✅ New Method: Fetch All Greetings
    public List<String> getAllGreetings() {
        List<Greeting> greetings = greetingRepository.findAll();
        return greetings.stream()
                .map(greeting -> "ID: " + greeting.getId() + ", Message: " + greeting.getMessage())
                .collect(Collectors.toList());
    }

    //UC7
    // ✅ New Method: Update Greeting by ID
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

    //UC8
    // ✅ New Method: Delete Greeting by ID
    public String deleteGreeting(Long id) {
        if (greetingRepository.existsById(id)) {
            greetingRepository.deleteById(id);
            return "Deleted Greeting ID: " + id;
        } else {
            return "Greeting not found for ID: " + id;
        }
    }
}

