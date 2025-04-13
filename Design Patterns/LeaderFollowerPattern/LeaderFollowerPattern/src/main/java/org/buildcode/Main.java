package org.buildcode;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new LinkedBlockingQueue<>();

        Thread leader = new Thread(new Leader(blockingQueue));
        leader.start();

        for(int i = 0; i < 3; i++) {
            new Thread(new Follower(blockingQueue)).start();
        }
    }
}