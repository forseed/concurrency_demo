package demo.lock;

/**
 * 改进简单的lock
 */
public class SimpleLockPlus {
    private long locks = 0;

    private Thread thread;

    public synchronized void lock() {
        while (locks > 0 && thread != Thread.currentThread()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        thread = Thread.currentThread();
        locks++;
    }

    public synchronized void unlock() {
        if (locks == 0 || thread != Thread.currentThread()) {
            return;
        }

        locks--;

        if (locks == 0) {
            notifyAll();
            thread = null;
        }
    }
}
