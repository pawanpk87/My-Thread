import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintOddAndEvenNumbers {
    public static void main(String[] args) {

        printOddAndEvenNumbersUsingExecutorService();

        // printOddAndEvenNumbers();
    }

    private static void printOddAndEvenNumbersUsingExecutorService() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable even = () -> {
            // print even numbers
            for (int i = 0; i < Integer.MAX_VALUE; i += 2) {
                System.out.println("evenNum: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {

                }
            }
        };

        Runnable odd = () -> {
            // print odd numbers
            for (int i = 1; i < Integer.MAX_VALUE; i += 2) {
                System.out.println("oddNum: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {

                }
            }
        };

        executor.submit(even);
        executor.submit(odd);

        executor.shutdown();
    }

    private static void printOddAndEvenNumbers() {
        Thread thread1 = new Thread(() -> {
            // print even numbers
            for (int i = 0; i < Integer.MAX_VALUE; i += 2) {
                System.out.println("evenNum: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {

                }
            }
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            // print odd numbers
            for (int i = 1; i < Integer.MAX_VALUE; i += 2) {
                System.out.println("oddNum: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {

                }
            }
        }, "thread2");

        thread1.start();
        thread2.start();

    }
}
