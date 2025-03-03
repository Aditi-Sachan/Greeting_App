package com.example.GreetingApp.Service;

import com.example.GreetingApp.Model.Greeting;
import com.example.GreetingApp.Repository.GreetingRepository;
import org.springframework.stereotype.Service;

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

         final GreetingRepository greetingRepository = null;

        // Constructor-based injection (Spring will inject repository)
    public GreetingService(GreetingRepository greetingRepository) {
            this.greetingRepository = greetingRepository;
        }

        public String getGreeting (String firstName, String lastName){
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

            // ✅ Fix: Use 'new Greeting()' and ensure repository is injected
            Greeting greeting = new Greeting(message);
            greetingRepository.save(greeting);  // Save to database

            return message;
        }
    }
}


