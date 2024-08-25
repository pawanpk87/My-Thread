package org.buildcode;

public class Barrier {
    private int totalThreads;
    private int waitingThreads;
    private int releasedThreads;

    public Barrier(int totalThreads) {
        this.totalThreads = totalThreads;
        this.waitingThreads = 0;
        this.releasedThreads = 0;
    }

    synchronized public void await() throws InterruptedException {
        while (waitingThreads == totalThreads) {
            wait();
        }

        waitingThreads++;

        if(waitingThreads == totalThreads) {
            notifyAll();
            releasedThreads = totalThreads;
        } else {
            /*
            * there could be Spurious Wake-ups
            * Spurious Wake-ups: A thread can also wake up without being notified, interrupted,
            *                    or timing out a so-called spurious wakeup.
            *
            * wait();
            * */

            while (waitingThreads < totalThreads) {
                wait();
            }
        }

        releasedThreads--;
        if (releasedThreads == 0) {
            waitingThreads = 0;
            notifyAll();
        }
    }
}