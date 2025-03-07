package com.example.GreetingApp.interfaces;

import com.example.GreetingApp.model.Greeting;
import java.util.List;

public interface IGreetingService {
    String getSimpleGreeting();
    Greeting saveGreeting(String firstName, String lastName);
    Greeting getGreetingById(Long id);
    List<Greeting> getAllGreetings();
    Greeting updateGreeting(Long id, String message);
    void deleteGreetingbyrepo(Long id);
}

