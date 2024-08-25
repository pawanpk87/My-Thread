package org.buildcode;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue();

        Thread thread1 = new Thread(new Producer(messageQueue));
        Thread thread2 = new Thread(new Consumer(messageQueue));

        thread1.start();
        thread2.start();
    }
}