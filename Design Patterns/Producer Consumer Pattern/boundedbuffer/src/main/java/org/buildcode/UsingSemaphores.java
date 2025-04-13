package org.buildcode;

class CountingSemaphore {
    int count;
    int usedPermits = 0;

    public CountingSemaphore(int count) {
        this.count = count;
    }

    public CountingSemaphore(int count, int initialPermits) {
        this.count = count;
        this.usedPermits = this.count - initialPermits;
    }

    synchronized public void acquire() throws InterruptedException {
        while (usedPermits == count) {
            wait();
        }
        usedPermits++;
        notify();
    }

    synchronized public void release() throws InterruptedException {
        while (usedPermits == 0) {
            wait();
        }
        usedPermits--;
        notify();
    }
}

class BlockingQueueWithSemaphore<T> {
    T[] array;

    int tail = 0;
    int head = 0;
    int size = 0;

    int capacity;

    CountingSemaphore binarySemaphore = new CountingSemaphore(1, 1);
    CountingSemaphore producerSemaphore;
    CountingSemaphore consumerSemaphore;

    public BlockingQueueWithSemaphore(int capacity) {
        this.capacity = capacity;
        this.producerSemaphore = new CountingSemaphore(this.capacity);
        this.consumerSemaphore = new CountingSemaphore(this.capacity, 0);

        array = (T[]) new Object[this.capacity];
    }

    public void enqueue(T item) throws InterruptedException {
        producerSemaphore.acquire();

        // acquire the mutex lock (It'll make sure that only one thread access the critical section
        binarySemaphore.acquire();

        if(tail == capacity) {
            tail = 0;
        }

        array[tail++] = item;
        size++;

        binarySemaphore.release();

        consumerSemaphore.release();
    }

    public T dequeue() throws InterruptedException {
        T value = null;

        consumerSemaphore.acquire();

        binarySemaphore.acquire();

        if(head == capacity) {
            head = 0;
        }

        value = array[head];
        array[head++] = null;
        size--;

        binarySemaphore.release();

        producerSemaphore.release();

        return value;
    }
}

public class UsingSemaphores {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueueWithSemaphore<Integer>  queue = new BlockingQueueWithSemaphore(3);

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queue.enqueue(10);
                    System.out.println("enqueued: " + 10);

                    queue.enqueue(20);
                    System.out.println("enqueued: " + 20);

                    queue.enqueue(30);
                    System.out.println("enqueued: " + 30);

                    queue.enqueue(40); // this will wait until dequeue happens
                    System.out.println("enqueued: " + 40);
                } catch (InterruptedException ex) {

                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000); // ensure some enqueue happen first
                    System.out.println("dequeued : " + queue.dequeue());
                } catch (InterruptedException e) {

                }
            }
        });

        consumer.start();
        producer.start();

        producer.join();
        consumer.join();
    }
}