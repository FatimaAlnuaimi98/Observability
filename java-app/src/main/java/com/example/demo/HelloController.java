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



@RestController
public class HelloController {

     // In-memory list to store bakeries
    private final List<Bakery> bakeryList = new ArrayList<>();
     private static final Tracer tracer = GlobalOpenTelemetry.getTracer("bakery-service");
    private static final Meter meter = GlobalOpenTelemetry.getMeter("bakery-service");
    private final LongCounter breadCounter;
    
    public HelloController() {
    breadCounter = meter.counterBuilder("bread.added")
        .setDescription("Number of breads added")
        .setUnit("breads")
        .build();
}

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
         Span span = Span.current();
        span.setAttribute("bread.name", bakery.getName());
        span.setAttribute("bread.price", bakery.getPrice());
        breadCounter.add(1);
        bakeryList.add(bakery);
        return bakery.toString();
    }

}
