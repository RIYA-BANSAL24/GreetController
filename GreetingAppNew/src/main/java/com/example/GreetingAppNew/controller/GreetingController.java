package com.example.GreetingAppNew.controller;

import com.example.GreetingAppNew.model.Greeting;
import com.example.GreetingAppNew.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private  final GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @PostMapping
    public Greeting createGreeting(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName) {

        return greetingService.saveGreeting(firstName, lastName);
    }

    @GetMapping("/all")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    @GetMapping("/{id}")
    public Greeting getGreetingById(@PathVariable Long id) {
        return greetingService.getGreetingById(id);
    }

    @PutMapping("/update/{id}")
    public Greeting updateGreeting(@PathVariable Long id, @RequestParam String newMessage) {
        return greetingService.updateGreeting(id, newMessage);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteGreeting(@PathVariable Long id) {
        greetingService.deleteGreeting(id);
        return "ID " + id + " has been successfully deleted.";
    }
    
//    @GetMapping
//    public Greeting getGreeting(
//            @RequestParam(value = "firstName", required = false) String firstName,
//            @RequestParam(value = "lastName", required = false) String lastName) {
//
//        return greetingService.getGreetingMessage(firstName, lastName);
//    }

//    @GetMapping
//    public Greeting getGreeting() {
//        return greetingService.getGreetingMessage();
//    }

//    @GetMapping
//    public Greeting getGreeting() {
//        return new Greeting("Hello, This is a GET Request!");
//    }

//    @PostMapping
//    public Greeting postGreeting(@RequestBody Greeting greeting) {
//        return new Greeting("I have Received this via POST Request: " + greeting.getMessage());
//    }
//
//    @PutMapping
//    public Greeting putGreeting(@RequestBody Greeting greeting) {
//        return new Greeting("This has been Updated via PUT Request: " + greeting.getMessage());
//    }
//
//    @DeleteMapping
//    public Greeting deleteGreeting() {
//        return new Greeting("Your request is  deleted successfully!");
//    }
}

