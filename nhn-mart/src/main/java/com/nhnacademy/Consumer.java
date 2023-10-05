package com.nhnacademy;

import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {
    private Store store;
    private Thread thread;


    public Consumer(String name, Store store) {
        this.thread = new Thread(this, name);
        this.store = store;
    }

    public String getName() {
        return thread.getName();
    }

    @Override
    public void run() {
        try {
            System.out.println(getName() + " enter");
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 10000));

            store.sell(this);
            store.exit(this);
        } catch (InterruptedException e) {
            thread.interrupt();
        }
    }

}