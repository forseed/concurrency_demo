package demo.lock;

import java.util.concurrent.Semaphore;

public class SemaphoreLock {
    private final Semaphore LOCK = new Semaphore(1);
    private Thread current;

    public void lock() throws InterruptedException {
        LOCK.acquire();
        current = Thread.currentThread();
    }

    public void unlock() {
        if (current != Thread.currentThread()) {
            return;
        }
        LOCK.release();
    }
}
