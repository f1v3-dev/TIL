package com.nhnacademy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 매장은 물건을 납품 받아서 판매한다.
 * 매장에는 최대 10개의 물건만 전시할 수 있다.
 * 매장은 최대 5명까지만 동시 입장 가능하다.
 * 매장에서 물건 구매는 동시에 1명만 가능하다.
 * 매장에서 물건 판매 후 빈 공간에 생기면 생산자에게 알려 준다.
 * 매장에서 물건 납품은 동시에 1명만 가능하다.
 */
public class Store {

    List<String> itemList;
    ExecutorService consumerPool;

    int maxCount = 10;

    public Store() {
        itemList = new ArrayList<>();

        consumerPool = Executors.newFixedThreadPool(5);
    }

    public void enter(Consumer consumer) {
        consumerPool.submit(consumer);
    }

    public void exit(Consumer consumer) {
        System.out.println(consumer.getName() + " exit");
    }

    public synchronized void buy(String item) {
        while (itemList.size() >= maxCount) {
            try {
                wait();
            }catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        itemList.add(item);
        notifyAll();
    }

    public synchronized void sell(Consumer consumer) {
        while (itemList.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            }
        }

        String item = itemList.remove(0);
        System.out.println(consumer.getName() + "에게 " + item + "이(가) 판매되었습니다.");

        notifyAll();
    }
}