package com.designpatterns.strategy;

public class Item {

    private String name;

    private int price;

    public Item(String name, int cost) {
        this.name = name;
        this.price = cost;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }
}
