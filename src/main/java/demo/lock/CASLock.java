package demo.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class CASLock {
    final AtomicInteger atomicInteger = new AtomicInteger(0);

    private Thread current;

    public void lock() {
        while (!atomicInteger.compareAndSet(0, 1)) {

        }
        current = Thread.currentThread();
    }

    public void unlock() {
        if (atomicInteger.get() == 0) {
            return;
        }

        if (current == Thread.currentThread()) {
            atomicInteger.compareAndSet(1, 0);
        }
    }
}



