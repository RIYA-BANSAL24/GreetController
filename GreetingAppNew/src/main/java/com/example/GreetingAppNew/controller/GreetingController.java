package com.example.GreetingAppNew.controller;

import com.example.GreetingAppNew.model.Greeting;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping
    public Greeting getGreeting() {
        return new Greeting("Hello, This is a GET Request!");
    }

    @PostMapping
    public Greeting postGreeting(@RequestBody Greeting greeting) {
        return new Greeting("I have Received this via POST Request: " + greeting.getMessage());
    }

    @PutMapping
    public Greeting putGreeting(@RequestBody Greeting greeting) {
        return new Greeting("This has been Updated via PUT Request: " + greeting.getMessage());
    }

    @DeleteMapping
    public Greeting deleteGreeting() {
        return new Greeting("Your request is  deleted successfully!");
    }
}

