package org.buildcode;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class UberCar {
    private final CyclicBarrier cyclicBarrier;
    private final ReentrantLock reentrantLock;

    private Semaphore democratsWaiting;
    private Semaphore republicansWaiting;

    private int republicans;
    private int democrats;

    public UberCar() {
        this.cyclicBarrier = new CyclicBarrier(4);
        this.reentrantLock = new ReentrantLock();

        this.democratsWaiting = new Semaphore(0);
        this.republicansWaiting = new Semaphore(0);

        this.republicans = 0;
        this.democrats = 0;
    }

    void acquireSeatForDemocrat() throws InterruptedException, BrokenBarrierException {
        boolean rideLeader = false;

        reentrantLock.lock();

        democrats++;

        if(democrats == 4) {
            democratsWaiting.release(3);
            democrats -= 4;
            rideLeader = true;
        } else if(democrats == 2 && republicans >= 2) {
            democratsWaiting.release(1);
            republicansWaiting.release(2);
            rideLeader = true;
            democrats -= 2;
            republicans -= 2;
        } else {
            reentrantLock.unlock();
            democratsWaiting.acquire();
        }

        seated();

        cyclicBarrier.await();

        if(rideLeader) {
            driver();
            reentrantLock.unlock();
        }
    }

    void acquireSeatForRepublican() throws InterruptedException, BrokenBarrierException {
        boolean rideLeader = false;

        reentrantLock.lock();

        republicans++;

        if(republicans == 4) {
            republicansWaiting.release(3);
            rideLeader = true;
            republicans -= 4;
        } else if(republicans == 2 && democrats >= 2) {
            republicansWaiting.release(1);
            democratsWaiting.release(2);
            rideLeader = true;
            republicans -= 2;
            democrats -= 2;
        } else {
            reentrantLock.unlock();
            republicansWaiting.acquire();
        }

        seated();

        cyclicBarrier.await();

        if(rideLeader) {
            driver();
            reentrantLock.unlock();
        }
    }

    void seated() {
        System.out.println(Thread.currentThread().getName() + " seated");
    }

    void driver() {
        System.out.println("Ride started...");
        System.out.println("Ride Leader: " + Thread.currentThread().getName());
        System.out.println("-------------------------- Ride completed -------------------------------");
        System.out.flush();
    }
}