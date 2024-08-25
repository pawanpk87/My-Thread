package org.buildcode;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final Barrier barrier = new Barrier(3);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread1");
                    barrier.await();

                    System.out.println("Thread1");
                    barrier.await();

                    System.out.println("Thread1");
                    barrier.await();
                } catch (InterruptedException exception) {

                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    System.out.println("Thread2");
                    barrier.await();

                    Thread.sleep(500);
                    System.out.println("Thread2");
                    barrier.await();

                    Thread.sleep(500);
                    System.out.println("Thread2");
                    barrier.await();
                } catch (InterruptedException exception) {

                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("Thread3");
                    barrier.await();

                    Thread.sleep(2000);
                    System.out.println("Thread3");
                    barrier.await();

                    Thread.sleep(2000);
                    System.out.println("Thread3");
                    barrier.await();
                } catch (InterruptedException exception) {

                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }
}