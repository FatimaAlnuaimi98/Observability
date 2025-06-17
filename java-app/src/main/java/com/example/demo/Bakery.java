package com.example.demo;

public class Bakery {
    private double price;
    private String name;

    // Default constructor needed for JSON deserialization
    public Bakery() {
    }

    public Bakery(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Added bakery {" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
