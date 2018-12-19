package demo.lock;

/**
 * 简单的lock(问题很多)
 */
public final class SimpleLock {
    private boolean busy = false;

    public synchronized void lock() {
        while (busy) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        busy = true;
    }

    public synchronized void unlock() {
        busy = false;
        this.notifyAll();
    }
}
