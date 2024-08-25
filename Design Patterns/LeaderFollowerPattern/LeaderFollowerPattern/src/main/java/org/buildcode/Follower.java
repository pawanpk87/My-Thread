package org.buildcode;

import java.util.concurrent.BlockingQueue;

public class Follower implements Runnable {
    private final BlockingQueue queue;

    public Follower(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Runnable task = (Runnable) queue.take();
                task.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}