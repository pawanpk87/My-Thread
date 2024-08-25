package org.buildcode;

import java.util.ArrayList;
import java.util.List;

//class MultithreadedTokenBucket {
//    private final long MAX_TOKENS;
//    private long availableTokens;
//
//    public MultithreadedTokenBucket(int maxToken) {
//        MAX_TOKENS = maxToken;
//        availableTokens = 0;
//
//       // We should not start thread inside constructor
//        Thread dThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                daemonThread();
//            }
//        });
//        dThread.setDaemon(true);
//        dThread.start();
//    }
//
//    private void daemonThread() {
//        while (true) {
//            synchronized (this) {
//                if(availableTokens < MAX_TOKENS) {
//                    availableTokens++;
//                    System.out.println("One token is added to bucket!");
//                }
//                notify();
//            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//
//            }
//        }
//    }
//
//    void getToken() throws InterruptedException {
//        synchronized (this) {
//            while(availableTokens == 0) {
//                wait();
//            }
//            availableTokens--;
//        }
//        System.out.println("Given token to thread: " +
//                Thread.currentThread().getName() +
//                " at: " + (System.currentTimeMillis() / 1000));
//    }
//}

// Using Factory Design Pattern
abstract class TokenBuck {
    public void getToken () throws InterruptedException {

    }
}

final class TokenBucketFactory {
    private TokenBucketFactory () {

    }

    static public TokenBucket makeTokenBucket(int capacity) {
        MultithreadedTokenBucket multithreadedTokenBucket = new MultithreadedTokenBucket(capacity);
        multithreadedTokenBucket.initialize();
        return multithreadedTokenBucket;
    }

    private static class MultithreadedTokenBucket extends TokenBucket {
        private final long MAX_TOKENS;
        private long availableTokens;

        MultithreadedTokenBucket(int maxToken) {
            super(maxToken);
            MAX_TOKENS = maxToken;
            availableTokens = 0;
        }

        private void initialize() {
            Thread dThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    daemonThread();
                }
            });
            dThread.setDaemon(true);
            dThread.start();
        }

        private void daemonThread() {
            while (true) {
                synchronized (this) {
                    if(availableTokens < MAX_TOKENS) {
                        availableTokens++;
                        System.out.println("One token is added to bucket!");
                    }
                    notify();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {

                }
            }
        }

        void getToken() throws InterruptedException {
            synchronized (this) {
                while(availableTokens == 0) {
                    wait();
                }
                availableTokens--;
            }
            System.out.println("Given token to thread: " +
                    Thread.currentThread().getName() +
                    " at: " + (System.currentTimeMillis() / 1000));
        }
    }
}

public class UsingBackgroundThread {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> allThreads = new ArrayList<>();

        final TokenBucket tokenBucket = TokenBucketFactory.makeTokenBucket(1);

        for(int i = 0; i < 10; i++) {
            Thread currThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        tokenBucket.getToken();
                    } catch (InterruptedException e) {

                    }
                }
            });
            currThread.setName("thread_"+i);
            allThreads.add(currThread);
        }

        for (Thread currThread : allThreads) {
            currThread.start();
        }

        for (Thread currThread : allThreads) {
            currThread.join();
        }
    }
}
