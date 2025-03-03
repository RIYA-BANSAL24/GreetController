package com.example.GreetingAppNew.service;

import com.example.GreetingAppNew.model.Greeting;
import com.example.GreetingAppNew.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    @Autowired
    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting saveGreeting(String firstName, String lastName) {
        String message;
        if (firstName != null && lastName != null) {
            message = "Hello, " + firstName + " " + lastName + "!";
        }
        else if (firstName != null) {
            message = "Hello, " + firstName + "!";
        }
        else if (lastName != null) {
            message = "Hello, " + lastName + "!";
        }
        else {
            message = "Invalid firstName and lastName!";
        }

        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }

    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    public Greeting getGreetingById(Long id) {
        Optional<Greeting> greeting = greetingRepository.findById(id);
        return greeting.orElse(null);
    }

    public Greeting updateGreeting(Long id, String newMessage) {
        return greetingRepository.findById(id)
                .map(greeting -> {
                    greeting.setMessage(newMessage);
                    return greetingRepository.save(greeting);
                })
                .orElseThrow(() -> new RuntimeException("Not found with ID: " + id));
    }

    public void deleteGreeting(Long id) {
        if (!greetingRepository.existsById(id)) {
            throw new RuntimeException("Greeting not found with ID: " + id);
        }
        greetingRepository.deleteById(id);
    }
}



