package org.buildcode;

public class MonitorObject {
    private boolean isAvailable;

    public MonitorObject() {
        this.isAvailable = true;
    }

    synchronized public void acquire() throws InterruptedException {
        while (!isAvailable) {
            wait();
        }
        isAvailable = false;
    }

    synchronized public void release() {
        isAvailable = true;
        notify();
    }
}