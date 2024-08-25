package org.buildcode;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final DiningPhilosophers diningPhilosophers = new DiningPhilosophers();

        Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
                startPhilosopher(diningPhilosophers, 0);
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                startPhilosopher(diningPhilosophers, 1);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                startPhilosopher(diningPhilosophers, 2);
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                startPhilosopher(diningPhilosophers, 3);
            }
        });

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                startPhilosopher(diningPhilosophers, 4);
            }
        });

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread0.join();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
    }

    public static void startPhilosopher(DiningPhilosophers diningPhilosophers, int id) {
        try {
            diningPhilosophers.lifeCycleOfPhilosopher(id);
        } catch (InterruptedException e) {

        }
    }
}