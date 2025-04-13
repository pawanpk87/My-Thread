package org.buildcode;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

class NonblockingStack<T> {
    private AtomicReference<Node<T>> topPtr = new AtomicReference<>();
    private AtomicInteger count = new AtomicInteger(0);

    public int size() {
        return count.get();
    }

    public void push(T item) {
        Node<T> oldTopPtr;
        Node<T> newTopPtr;
        do {
            oldTopPtr = topPtr.get();
            newTopPtr = new Node<>(item);
            newTopPtr.setNextPtr(oldTopPtr);
        } while (!topPtr.compareAndSet(oldTopPtr, newTopPtr));
        count.incrementAndGet();
    }

    public T pop() {
        Node<T> oldTopPtr;
        Node<T> newTopPtr;
        do {
            oldTopPtr = topPtr.get();
            if(oldTopPtr == null) {
                return null;
            }
            newTopPtr = oldTopPtr.getNextPtr();
        } while (!topPtr.compareAndSet(oldTopPtr, newTopPtr));
        count.decrementAndGet();
        return oldTopPtr.getItem();
    }
}

public class UsingAtomicReference {
    public static void main(String[] args) {

    }
}