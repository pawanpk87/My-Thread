package org.buildcode;

import java.util.ArrayList;
import java.util.List;

class TokenBucket {
    private final int MAX_TOKENS;
    private long lastRequestedTime;
    private long availableTokens;

    public TokenBucket(int maxToken) {
        MAX_TOKENS = maxToken;
        lastRequestedTime = System.currentTimeMillis();
        availableTokens = 0;
    }

    synchronized void getToken() throws InterruptedException {
        // tokens generated since the last request
        availableTokens += (System.currentTimeMillis() - lastRequestedTime) / 1000;

        if(availableTokens > MAX_TOKENS) {
            availableTokens = MAX_TOKENS;
        }

        if(availableTokens == 0) {
            Thread.sleep(1000);
        } else {
            availableTokens--;
        }

        lastRequestedTime = System.currentTimeMillis();

        System.out.println("Given token to thread: " +
                Thread.currentThread().getName() +
                " at: " + (System.currentTimeMillis() / 1000));
    }
}

public class UsingTimePointer {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> allThreads = new ArrayList<>();
        final TokenBucket tokenBucket = new TokenBucket(1);

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
