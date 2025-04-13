package org.buildcode;

public class Consumer implements Runnable {
    private final MessageQueue messageQueue;

    public Consumer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        for (String message = messageQueue.receive(); !("done".equals(message)); message = messageQueue.receive()) {
            System.out.println("Consumed: " + message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
    }
}