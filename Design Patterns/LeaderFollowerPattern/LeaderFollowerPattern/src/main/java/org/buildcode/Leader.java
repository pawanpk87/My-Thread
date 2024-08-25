package org.buildcode;

import java.util.concurrent.BlockingQueue;

public class Leader implements Runnable {
    private final BlockingQueue queue;

    public Leader(BlockingQueue blockingQueue) {
        this.queue = blockingQueue;
    }

    @Override
    public void run() {
        int taskId = 1;

        while (true) {
            try {
                Runnable task = () -> {
                    System.out.println("Task executed by " + Thread.currentThread().getName());
                };

                queue.put(task);

                System.out.println("Leader added the task: " + taskId);

                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}