package org.buildcode;

public class Producer implements Runnable {
    private MessageQueue messageQueue;

    public Producer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        String[] messages = new String[] {"Message 0", "Message 1", "Message 2", "Message 3", "done"};
        for (String message : messages) {
            messageQueue.send(message);
            System.out.println("Produced: " + message);
            try {
                Thread.sleep(500);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
    }
}