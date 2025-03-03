package com.example.GreetingAppNew.controller;

import com.example.GreetingAppNew.model.Greeting;
import com.example.GreetingAppNew.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private  final GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public Greeting getGreeting(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName) {

        return greetingService.getGreetingMessage(firstName, lastName);
    }

//    @GetMapping
//    public Greeting getGreeting() {
//        return greetingService.getGreetingMessage();
//    }

//    @GetMapping
//    public Greeting getGreeting() {
//        return new Greeting("Hello, This is a GET Request!");
//    }

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

