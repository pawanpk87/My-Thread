package org.buildcode;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final UnisexBathroom bathroom = new UnisexBathroom();

        Thread female1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bathroom.acquireBathroomForWomen("female1");
                } catch (InterruptedException e) {

                }
            }
        });

        Thread male1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bathroom.acquireBathroomForMale("male1");
                } catch (InterruptedException e) {

                }
            }
        });

        Thread male2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bathroom.acquireBathroomForMale("male2");
                } catch (InterruptedException e) {

                }
            }
        });

        Thread male3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bathroom.acquireBathroomForMale("male3");
                } catch (InterruptedException e) {

                }
            }
        });

        Thread male4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bathroom.acquireBathroomForMale("male4");
                } catch (InterruptedException e) {

                }
            }
        });

        Thread female2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bathroom.acquireBathroomForWomen("female2");
                } catch (InterruptedException e) {

                }
            }
        });

        female1.start();
        male1.start();
        male2.start();
        male3.start();
        male4.start();
        female2.start();

        female1.join();
        male1.join();
        male2.join();
        male3.join();
        male4.join();
        female2.join();
    }
}