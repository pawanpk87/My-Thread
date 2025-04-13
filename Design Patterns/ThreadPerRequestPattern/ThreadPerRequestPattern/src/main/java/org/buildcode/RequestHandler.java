package org.buildcode;

public class RequestHandler implements Runnable {
    private final Request request;

    public RequestHandler(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        System.out.println("Processing request: " + request.requestData() + " in " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Finished processing request");
    }
}