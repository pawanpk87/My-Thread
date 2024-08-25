package org.buildcode;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final ReadWriteLock readWriteLock = new ReadWriteLock();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Attempting to acquire write lock by thread1: " + System.currentTimeMillis());
                    readWriteLock.acquireWriteLock();
                    System.out.println("Write lock acquired by thread1: " + System.currentTimeMillis());

                    /*
                     *  It is possible for writer thread to never get a chance to acquire the write lock
                     *  since there could be always be at least one reader which has the read lock acquired
                     * */

                    // Let's simulate this by acquiring the lock for indefinitely
                    // (that means no other write will be able to acquire lock)
                    while (true) {
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException exception) {

                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Attempting to acquire write lock by thread2: " + System.currentTimeMillis());
                    readWriteLock.acquireWriteLock();
                    System.out.println("Write lock acquired by thread2: " + System.currentTimeMillis());
                } catch (InterruptedException exception) {

                }
            }
        });

        Thread reader1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    readWriteLock.acquireReadLock();
                    System.out.println("Read lock acquired by reader1: " + System.currentTimeMillis());
                } catch (InterruptedException exception) {

                }
            }
        });

        Thread reader2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    readWriteLock.acquireReadLock();
                    System.out.println("Read lock acquired by reader2: " + System.currentTimeMillis());
                } catch (InterruptedException exception) {

                }
            }
        });

        reader1.start();
        thread1.start();

        Thread.sleep(2000);

        reader2.start();

        Thread.sleep(2000);

        thread2.start();

        reader1.join();
        reader2.join();

        thread2.join();
    }
}