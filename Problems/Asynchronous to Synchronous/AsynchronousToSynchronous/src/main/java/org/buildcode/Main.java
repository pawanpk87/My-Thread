package org.buildcode;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Executor executor = new SynchronousExecutor();

        // We have to make this asynchronous
        executor.asynchronousExecution(() -> {
            System.out.println("The task is done!");
        });

        System.out.println("Main thread exiting...");

        /*
        Solution: 1

        Executor executor = new Executor();

        Semaphore semaphore = new Semaphore(0);

        executor.asynchronousExecution(() -> {
            System.out.println("The task is done!");
            semaphore.release();
        });

        semaphore.acquire();

        System.out.println("Main thread exiting...");

        * */


    }
}