package demo.lock.caslock;

import java.util.concurrent.atomic.AtomicInteger;

public class CASLock {
    final AtomicInteger atomicInteger = new AtomicInteger(0);

    public void lock() throws LockException {
        boolean res = atomicInteger.compareAndSet(0, 1);
        if (!res) {
            throw new LockException("has lock..");
        }
    }

    public void unlock() {
        if (atomicInteger.get() == 0) {
            return;
        }

        atomicInteger.compareAndSet(1, 0);
    }
}
