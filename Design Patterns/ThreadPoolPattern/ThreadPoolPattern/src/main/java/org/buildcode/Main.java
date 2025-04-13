package org.buildcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Create a thread pool with a fixed number of threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit tasks to pool
        for(int i = 0; i < 10; i++) {
            executorService.submit(new Task("task_" + i));
        }

        // shutdown of pool
        executorService.shutdown();

        executorService.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("All task completed!");
    }
}