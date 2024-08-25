package org.buildcode.scheduler;

import org.buildcode.methodrequest.MethodRequest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Scheduler implements Runnable {
    private final BlockingQueue<MethodRequest> requestQueue;

    public Scheduler() {
        this.requestQueue = new LinkedBlockingQueue<>();
    }

    public void enqueue(MethodRequest methodRequest) {
        try{
            requestQueue.put(methodRequest);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                MethodRequest currMethodRequest = requestQueue.take();
                currMethodRequest.execute();
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}