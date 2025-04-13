package org.buildcode;

public class Worker implements Runnable {
    private final MonitorObject monitorObject;
    private final String name;

    public Worker(MonitorObject monitorObject, String name) {
        this.monitorObject = monitorObject;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            monitorObject.acquire();
            System.out.println(name + " acquired the monitor.");
            Thread.sleep(1000);
            monitorObject.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}