class PrintEvenAndOdd {
    private Boolean isEvenTurn;
    private final Object lock;

    public PrintEvenAndOdd() {
        isEvenTurn = true;
        lock = new Object();
    }

    public void printEvenNumbers() {
        for (int i = 0; i < Integer.MAX_VALUE; i += 2) {
            synchronized (lock) {
                while (!isEvenTurn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Even: " + i);
                isEvenTurn = false;
                lock.notify();
            }
        }
    }

    public void printOddNumbers() {
        for (int i = 0; i < Integer.MAX_VALUE; i += 2) {
            synchronized (lock) {
                while (isEvenTurn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Odd: " + i);
                isEvenTurn = true;
                lock.notify();
            }
        }
    }
}

public class PrintOddAndEvenNumbersInOrder {
    public static void main(String[] args) {
        PrintEvenAndOdd printEvenAndOdd = new PrintEvenAndOdd();

        Thread evenThread = new Thread(() -> printEvenAndOdd.printEvenNumbers(), "EvenThread");
        Thread oddThread = new Thread(() -> printEvenAndOdd.printOddNumbers(), "OddThread");

        evenThread.start();
        oddThread.start();
    }
}