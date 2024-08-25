package org.buildcode;

public class Executor {
    public void asynchronousExecution(Callback callback) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException exception) {

                }
                callback.done();
            }
        });
        thread.start();
    }
}