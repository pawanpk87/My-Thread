package org.buildcode;

public class Node <T> {
    private T item;
    private Node<T> nextPtr;

    public Node(T item) {
        this.item = item;
        this.nextPtr = null;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Node<T> getNextPtr() {
        return nextPtr;
    }

    public void setNextPtr(Node<T> nextPtr) {
        this.nextPtr = nextPtr;
    }
}