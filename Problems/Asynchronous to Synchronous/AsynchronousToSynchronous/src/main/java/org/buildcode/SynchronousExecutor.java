package org.buildcode;

public class SynchronousExecutor extends Executor {

    @Override
    public void asynchronousExecution(Callback callback) throws InterruptedException {
        Object signal = new Object();

        // handle spurious wake-ups
        final boolean[] isDone = new boolean[1];

        Callback cb = new Callback() {
            @Override
            public void done() {
                callback.done();
                synchronized (signal) {
                    signal.notify();
                    isDone[0] = true;
                }
            }
        };

        super.asynchronousExecution(cb);

        synchronized (signal) {
            while (!isDone[0]) {
                signal.wait();
            }
        }
    }
}