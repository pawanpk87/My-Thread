package org.buildcode;

class BlockingQueue<T> {
    T[] array;

    Object lock = new Object();

    int size = 0;
    int tail = 0;
    int head = 0;

    int capacity;

    public BlockingQueue(int capacity) {
        array = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public void enqueue(T item) throws InterruptedException {
        synchronized (lock) {
            while (size == capacity) {
                lock.wait();
            }

            if (tail == capacity) {
                tail = 0;
            }

            array[tail++] = item;

            size++;

            lock.notifyAll();
        }
    }

    public T dequeue() throws InterruptedException {
        T value = null;
        synchronized (lock) {
            while (size == 0) {
                lock.wait();
            }

            if(head == capacity) {
                head = 0;
            }

            value = array[head];

            array[head] = null;

            head++;
            size--;

            lock.notifyAll();
        }
        return value;
    }
}

public class SimpleLock {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new BlockingQueue<>(3);

        Thread producer = new Thread(() -> {
            try {
                queue.enqueue(1);
                queue.enqueue(2);
                queue.enqueue(3);

                System.out.println("Queue full, next enqueue should block");

                queue.enqueue(4);
                System.out.println("Produced: 4");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(2000);
                int item = queue.dequeue();
                System.out.println("Consumed: " + item);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}