package org.buildcode;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BarberShop {
    private final int chairs;
    private int waitingCustomers;

    private final ReentrantLock lock;

    private final Semaphore waitForCustomerToEnter;
    private final Semaphore waitForBarberToGetReady;
    private final Semaphore waitForCustomerToLeave;
    private final Semaphore waitForBarberToCutHair;

    int hairCutGiven;

    public BarberShop() {
        this.chairs = 3;
        this.waitingCustomers = 0;

        this.lock = new ReentrantLock();

        this.waitForCustomerToEnter = new Semaphore(0);
        this.waitForBarberToGetReady = new Semaphore(0);
        this.waitForCustomerToLeave = new Semaphore(0);
        this.waitForBarberToCutHair = new Semaphore(0);

        this.hairCutGiven = 0;
    }

    public void customerWalksIn() throws InterruptedException {
        lock.lock();
        if(waitingCustomers == chairs) {
            System.out.println("Customer walks out, as all the chairs are occupied!");
            lock.unlock();
            return;
        }
        waitingCustomers++;
        lock.unlock();

        // let the barber know there is least one customer
        waitForCustomerToEnter.release();

        // wait for barber to get ready
        waitForBarberToGetReady.acquire();

        // hair cutting is in progress...

        // while hair cutting in progress give unoccupied seat to customer
        lock.lock();
        waitingCustomers--;
        lock.unlock();

        // wait for haircut to complete
        waitForBarberToCutHair.acquire();

        // leave the barber chair
        waitForCustomerToLeave.release();

//
//        lock.lock();
//        waitingCustomers--;
//        lock.unlock();
    }

    public void barber() throws InterruptedException {
        while (true) {
            // wait till the customer enters a shop
            waitForCustomerToEnter.acquire();

            // let the customer know barber is ready
            waitForBarberToGetReady.release();

            hairCutGiven++;
            System.out.println("Barber cutting hair... " + hairCutGiven);
            Thread.sleep(1000);

            // let the customer know, haircut is done
            waitForBarberToCutHair.release();

            // wait for customer to leave the barber chair
            waitForCustomerToLeave.acquire();
        }
    }
}