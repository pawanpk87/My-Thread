package org.buildcode;

public class FutureResult<T> {
    private T result;
    private boolean isReady = false;

    synchronized public void setResult(T result) {
        this.result = result;
        this.isReady = true;
        notifyAll();
    }

    synchronized public T getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException exception) {

            }
        }
        return result;
    }
}