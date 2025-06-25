package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.GlobalOpenTelemetry;

//javaappacr.azurecr.io

@RestController
public class Controller {

     // In-memory list to store users
    private final List<User> userList = new ArrayList<>();
     private static final Tracer tracer = GlobalOpenTelemetry.getTracer("user-service");
    private static final Meter meter = GlobalOpenTelemetry.getMeter("user-service");
    private final LongCounter userCounter;
    
    public Controller() {
    userCounter = meter.counterBuilder("user.added")
        .setDescription("Number of users added")
        .setUnit("users")
        .build();
}

    @GetMapping("/")
    public String hello() {
        return "Hello, please visit /users endpoint to see the list of users.";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userList;
    }

    @PostMapping("/add_user")
    public String createUser(@RequestBody User user) {
         Span span = Span.current();
        span.setAttribute("user.name", user.getName());
        span.setAttribute("user.id", user.getId());
        userCounter.add(1);
        userList.add(user);
        return user.toString();
    }

}
