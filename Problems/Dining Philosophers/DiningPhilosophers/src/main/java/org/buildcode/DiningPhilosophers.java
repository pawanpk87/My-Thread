package org.buildcode;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    private final Semaphore[] forks;
    private final Semaphore maxDiners;

    private static Random random = new Random(System.currentTimeMillis());

    public DiningPhilosophers() {
        forks = new Semaphore[5];
        maxDiners = new Semaphore(4);

        for(int i = 0; i < 5; i++) {
            forks[i] = new Semaphore(1);
        }
    }

    public void lifeCycleOfPhilosopher(int id) throws InterruptedException {
        while (true) {
            contemplate(id);
            eat(id);
        }
    }

    private void contemplate(int id) throws InterruptedException {
        System.out.println("Philosopher: " + id + " contemplating...");
        Thread.sleep(random.nextInt(500));
    }

    //Solution 1:- allow only 4 philosophers to sit on table
    private void eat(int id) throws InterruptedException {
        maxDiners.acquire();

        int leftFork = id;
        int rightFork = (id + 1) % 5;

        forks[leftFork].acquire();
        forks[rightFork].acquire();

        System.out.println("Philosopher: " + id + " eating...");

        forks[leftFork].release();
        forks[rightFork].release();

        maxDiners.release();
    }

    /**
        Solution 2: make any philosopher left-handed
        private void eat(int id) throws InterruptedException {
            if(id == 3) {
                acquireForkLeftHanded(3);
            } else {
                acquireForkRightHanded(id);
            }

            System.out.println("Philosopher: " + id + " eating...");

            int leftFork = id;
            int rightFork = (id + 1) % 5;

            forks[leftFork].acquire();
            forks[rightFork].acquire();
        }

        private void acquireForkRightHanded(int id) throws InterruptedException {
            int leftFork = id;
            int rightFork = (id + 1) % 5;

            forks[leftFork].acquire();
            forks[rightFork].acquire();
        }

        private void acquireForkLeftHanded(int id) throws InterruptedException {
            int leftFork = id;
            int rightFork = (id + 1) % 5;

            forks[rightFork].acquire();
            forks[leftFork].acquire();
        }
    */
}