package org.buildcode;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final BarberShop barberShop = new BarberShop();

        List<Thread> threads = new ArrayList<>();

        Thread barber = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    barberShop.barber();
                } catch (InterruptedException e) {

                }
            }
        });
        barber.start();

        for (int i = 0; i < 10; i++) {
            Thread currentThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        barberShop.customerWalksIn();
                    } catch (InterruptedException e) {

                    }
                }
            });
            currentThread.setName("Thread_" + i);
            threads.add(currentThread);
        }

        for (Thread currentThread : threads) {
            currentThread.start();
        }

        for (Thread currentThread : threads) {
            currentThread.join();
        }

        threads.clear();

        for (int i = 0; i < 10; i++) {
            Thread currentThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        barberShop.customerWalksIn();
                    } catch (InterruptedException e) {

                    }
                }
            });
            currentThread.setName("ThreadV2_" + i);
            threads.add(currentThread);
        }

        for (Thread currentThread : threads) {
            currentThread.start();
        }

        for (Thread currentThread : threads) {
            currentThread.join();
        }

        barber.join();
    }
}