package com.threading;

import java.util.concurrent.atomic.AtomicInteger;

public class Thread {
    static AtomicInteger threadId = new AtomicInteger(1);

    public void run() {
        System.out.println("Running Thread " + threadId.getAndIncrement());
    }

    public void start() {
        start0();
    }
    private native void start0();
}
