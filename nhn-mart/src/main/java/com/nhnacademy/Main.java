package com.nhnacademy;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();

        Producer producer = new Producer(store);
        producer.start();

        for (int i = 1; i <= 6; i++) {
            store.enter(new Consumer("consumer" + i, store));
        }
    }
}