package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

     // In-memory list to store bakeries
    private final List<Bakery> bakeryList = new ArrayList<>();

    @GetMapping("/")
    public String hello() {
        return "Hello, please visit /bakery endpoint to checkout the types of bread available.";
    }

    @GetMapping("/bakery")
    public List<Bakery>  getBakery() {
    
        return bakeryList;
    }

    @PostMapping("/add_bread")
    public String createGreeting(@RequestBody Bakery bakery) {
        bakeryList.add(bakery);
        // You could add business logic here like saving to a database
        return bakery.toString();
    }


    
}
