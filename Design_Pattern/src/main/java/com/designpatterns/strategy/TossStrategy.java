package com.designpatterns.strategy;

public class TossStrategy implements PaymentStrategy {

    private String email;

    private String password;

    public TossStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using Toss Card.");
    }
}
