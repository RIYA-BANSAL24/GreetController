package com.example.GreetingAppNew.service;

import com.example.GreetingAppNew.model.Greeting;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public Greeting getGreetingMessage() {
        return new Greeting("Hello World");
    }
}

