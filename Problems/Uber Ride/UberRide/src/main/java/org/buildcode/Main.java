package org.buildcode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final UberCar car = new UberCar();

        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        car.acquireSeatForDemocrat();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        System.out.println("Error occurred !");
                    }
                }
            });
            thread.setName("Democrat_" + i);
            threads.add(thread);
            Thread.sleep(20);
        }

        for(int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        car.acquireSeatForRepublican();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        System.out.println("Error occurred !");
                    }
                }
            });
            thread.setName("Republican" + i);
            threads.add(thread);
            Thread.sleep(30);
        }

        for (Thread currentThread : threads) {
            currentThread.start();
        }

        for (Thread currentThread : threads) {
            currentThread.join();
        }
    }
}