package com.threading;

import java.util.concurrent.atomic.AtomicInteger;

public class Thread {
    static AtomicInteger threadCount = new AtomicInteger(1);

    public void run() {
        System.out.println("Running Thread " + threadCount.getAndIncrement());
    }

    public void start() {
        start0();
    }
    private native void start0();
}
