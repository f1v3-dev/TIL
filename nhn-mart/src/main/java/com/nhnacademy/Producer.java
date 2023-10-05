package com.nhnacademy;

import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {

    private Thread thread;
    private Store store;

    public Producer(Store store) {
        this.thread = new Thread(this, "Producer");
        this.store = store;
    }

    public void start() {
        thread.start();
    }

    @Override
    public void run() {
        try {
            while (!thread.isInterrupted()) {
                System.out.println("\n생산자가 물건을 만들었습니다.\n");
                store.buy("item");
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 10000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}