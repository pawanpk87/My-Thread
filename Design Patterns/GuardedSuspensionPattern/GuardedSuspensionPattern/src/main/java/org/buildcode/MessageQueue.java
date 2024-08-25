package org.buildcode;

public class MessageQueue {
    private String message;
    private boolean hasMessage;

    public MessageQueue() {
        this.message = null;
        this.hasMessage = false;
    }

    synchronized public String receive() {
        while (!hasMessage) {
            System.out.println(Thread.currentThread().getName() + " is waiting for message...");
            try {
                wait(); // Suspension
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        hasMessage = false;
        String receivedMessage = this.message;

        notifyAll();

        return receivedMessage;
    }

    synchronized public void send(String message) {
        while (hasMessage) {
            System.out.println(Thread.currentThread().getName() + " is waiting for sending message...");
            try {
                wait(); // Suspension
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        hasMessage = true;
        this.message = message;

        notifyAll();
    }
}