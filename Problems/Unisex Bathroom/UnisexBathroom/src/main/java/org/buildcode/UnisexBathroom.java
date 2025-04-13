package org.buildcode;

import java.util.concurrent.Semaphore;

public class UnisexBathroom {
    private static String WOMEN = "women";
    private static String MEN = "men";
    private static String NONE = "none";

    private String inUseBy;
    private int employeesInBathroom;

    private Semaphore maxEmployeesAllow;

    public UnisexBathroom () {
        inUseBy = NONE;
        employeesInBathroom = 0;
        maxEmployeesAllow = new Semaphore(3);
    }

    void acquireBathroomForMale(String name) throws InterruptedException {
        synchronized (this) {
            while (inUseBy.equals(WOMEN)) {
                wait();
            }

            maxEmployeesAllow.acquire();

            inUseBy = MEN;
            employeesInBathroom++;
        }

        useBathroom(name);
        maxEmployeesAllow.release();

        synchronized (this) {
            employeesInBathroom--;
            if(employeesInBathroom == 0) {
                inUseBy = NONE;
            }
            notifyAll();
        }
    }

    void acquireBathroomForWomen(String name) throws InterruptedException {
        synchronized (this) {
            while (inUseBy.equals(MEN)) {
                wait();
            }

            maxEmployeesAllow.acquire();

            inUseBy = WOMEN;
            employeesInBathroom++;
        }

        useBathroom(name);
        maxEmployeesAllow.release();

        synchronized (this) {
            employeesInBathroom--;
            if(employeesInBathroom == 0) {
                inUseBy = NONE;
            }
            notifyAll();
        }
    }

    private void useBathroom(String name) throws InterruptedException {
        System.out.println(name + " using the bathroom.");
        System.out.println("Current employees in bathroom: " + employeesInBathroom);
        Thread.sleep(2000);
        System.out.println(name + " done using bathroom");
    }
}