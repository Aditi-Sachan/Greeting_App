package com.example.GreetingApp.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Greeting {

    // Getter for ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Getter for message
    // Setter for message
    @Setter
    @Column(nullable = false)  // Ensures the column is not null
    private String message;

    // Default constructor (required by JPA)
    public Greeting() {}

    // Constructor with message
    public Greeting(String message) {

        this.message = message;
    }

}



