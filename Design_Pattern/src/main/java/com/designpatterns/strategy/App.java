package com.designpatterns.strategy;

public class App {

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Item A = new Item("MacBook", 3000);
        Item B = new Item("iPad", 1500);

        cart.addItem(A);
        cart.addItem(B);

        // pay by kakao
        cart.pay(new KakaoStrategy("seungjo", "12341234", "513", "24/06"));

        // pay by toss
        cart.pay(new TossStrategy("seungjo@test.com", "THISISPASSWORD123!"));
    }
}
