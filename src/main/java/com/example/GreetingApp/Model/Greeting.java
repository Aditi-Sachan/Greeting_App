package com.example.GreetingApp.Model;

import jakarta.persistence.*;

@Entity
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)  // Ensures the column is not null
    private String message;

    // Default constructor (required by JPA)
    public Greeting() {}

    // Constructor with message
    public Greeting(String message) {
        this.message = message;
    }

    // Getter for ID
    public Long getId() {
        return id;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Setter for message
    public void setMessage(String message) {
        this.message = message;
    }
}



