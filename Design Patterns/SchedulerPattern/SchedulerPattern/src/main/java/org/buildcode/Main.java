package org.buildcode;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        Runnable task1 = () -> System.out.println("Task1 executed at: " + System.currentTimeMillis());
        Runnable task2 = () -> System.out.println("Task2 executed at: " + System.currentTimeMillis());

        scheduler.scheduleAtFixedRate(task1, 1, 3, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(task2, 2, 5, TimeUnit.SECONDS);

        Thread.sleep(15000);

        scheduler.shutdown();
    }
}