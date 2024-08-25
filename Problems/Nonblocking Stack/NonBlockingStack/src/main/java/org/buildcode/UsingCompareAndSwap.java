package org.buildcode;

import java.util.concurrent.*;

class SimulateCompareAndSwap<T> {
    private T value;

    public SimulateCompareAndSwap(T item) {
        this.value = item;
    }

    synchronized T getValue() {
        return this.value;
    }

    synchronized T compareAndSwap(T expectedValue, T newValue) {
        if(value == null) {
            if(expectedValue == null) {
                value = newValue;
            }
            return null;
        }

        if(value.equals(expectedValue)) {
            value = newValue;
            return expectedValue;
        }

        return value;
    }

    synchronized boolean compareAndSet(T expectedValue, T newValue) {
        T returnedValue = compareAndSwap(expectedValue, newValue);
        if(returnedValue == null && expectedValue == null) {
            return true;
        } else if(returnedValue == null && expectedValue != null) {
            return false;
        } else {
            return returnedValue.equals(expectedValue);
        }
    }
}

class CASBasedStack<T> {
    private SimulateCompareAndSwap<Node<T>> topPtr;

    public CASBasedStack() {
        topPtr = new SimulateCompareAndSwap<>(null);
    }

    public void push(T item) {
        Node<T> oldTopPtr;
        Node<T> newTopPtr;
        do {
            oldTopPtr = topPtr.getValue();
            newTopPtr = new Node<>(item);
            newTopPtr.setNextPtr(oldTopPtr);
        } while (!topPtr.compareAndSet(oldTopPtr, newTopPtr));
    }

    public T pop() {
        Node<T> returnedPtr;
        Node<T> newTopPtr;
        do {
            returnedPtr = topPtr.getValue();
            if(returnedPtr == null) {
                return null;
            }
            newTopPtr = returnedPtr.getNextPtr();
        } while (!topPtr.compareAndSet(returnedPtr, newTopPtr));
        return returnedPtr.getItem();
    }
}

public class UsingCompareAndSwap {
    public static void main(String[] args) throws InterruptedException {
        CASBasedStack<Integer> stack = new CASBasedStack<>();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        int numThreads = 2;

        CyclicBarrier barrier = new CyclicBarrier(numThreads);

        try {
            for (int i = 0; i < numThreads; i++) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10000; i++) {
                            stack.push(555);
                        }

                        try {
                            barrier.await();
                        } catch (BrokenBarrierException | InterruptedException e) {

                        }

                        for (int i = 0; i < 10000; i++) {
                            stack.pop();
                            //System.out.println(stack.pop());
                        }
                    }
                });
            }
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);
        }
    }
}