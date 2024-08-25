package org.buildcode;

public class ReadWriteLock {
    private int readers;
    private boolean isWriteLock;

    public ReadWriteLock() {
        readers = 0;
        isWriteLock = false;
    }

    synchronized public void acquireReadLock() throws InterruptedException {
        while (isWriteLock) {
            wait();
        }
        readers++;
    }

    synchronized public void releaseReadLock() {
        readers--;
        notify();
    }

    synchronized public void acquireWriteLock() throws InterruptedException {
        while (isWriteLock || readers != 0) {
            wait();
        }
        isWriteLock = true;
    }

    synchronized public void releaseWriteLock() {
        isWriteLock = false;
        notify();
    }
}