package org.buildcode;

class SynchronizedStack <T> {
    private Node<T> topPtr;

    synchronized public void push(T item) {
        if(topPtr == null) {
            topPtr = new Node<>(item);
        } else {
            Node<T> oldNode = topPtr;
            topPtr = new Node<>(item);
            topPtr.setNextPtr(oldNode);
        }
    }

    synchronized public T pop() {
        if(topPtr == null) {
            return null;
        }
        Node<T> oldNode = topPtr;
        topPtr = topPtr.getNextPtr();
        return oldNode.getItem();
    }
}

public class UsingSynchronizedStack {
    public static void main(String[] args) {

    }
}
