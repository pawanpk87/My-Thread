package org.buildcode;

public class Main {
    public static void main(String[] args) {
        MonitorObject monitor = new MonitorObject();

        Thread worker1 = new Thread(new Worker(monitor, "Worker1"));
        Thread worker2 = new Thread(new Worker(monitor, "Worker2"));

        worker1.start();
        worker2.start();
    }
}