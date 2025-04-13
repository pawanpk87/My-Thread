package org.buildcode;

import org.buildcode.proxy.ArithmeticProxy;
import org.buildcode.scheduler.Scheduler;
import org.buildcode.servant.ArithmeticServant;

public class Main {
    public static void main(String[] args) {
        ArithmeticServant servant = new ArithmeticServant();
        Scheduler scheduler = new Scheduler();

        ArithmeticProxy proxy = new ArithmeticProxy(scheduler, servant);

        Thread schedularThread = new Thread(scheduler);
        schedularThread.start();

        // Make the synchronous calls
        FutureResult<Integer> result1 = proxy.add(10, 20);
        FutureResult<Integer> result2 = proxy.subtract(10, 20);

        // main thread can perform diff task...

        // retrieve the value
        System.out.println("Result of addition: " + result1.getResult());
        System.out.println("Result of subtraction: " + result2.getResult());

        schedularThread.interrupt();
    }
}