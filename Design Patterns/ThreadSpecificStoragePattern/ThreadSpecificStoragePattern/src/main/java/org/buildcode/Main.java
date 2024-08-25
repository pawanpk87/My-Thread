package org.buildcode;

public class Main {
    private static ThreadLocal<Integer> var = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                var.set(100);
                System.out.println(Thread.currentThread().getName() + " initial value: " + var.get());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                var.set(var.get() + 30);
                System.out.println(Thread.currentThread().getName() + " updated value: " + var.get());
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                var.set(200);
                System.out.println(Thread.currentThread().getName() + " initial value: " + var.get());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                var.set(var.get() + 5);
                System.out.println(Thread.currentThread().getName() + " updated value: " + var.get());
            }
        });

        thread1.start();
        thread2.start();
    }
}