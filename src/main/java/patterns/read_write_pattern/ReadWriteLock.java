package patterns.read_write_pattern;

/**
 * 读写锁分离
 */
public class ReadWriteLock {
    private int readingReaders = 0;
    private int waitingReaders = 0;
    private int writingWriters = 0;
    private int waitingWriters = 0;
    private Boolean preferWrite = true;

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preferWrite) {
        this.preferWrite = preferWrite;
    }

    public synchronized void readLock() throws InterruptedException {
        this.waitingReaders++;
        try {
            while (writingWriters > 0 || (preferWrite && waitingWriters > 0)) {
                this.wait();
            }

            this.readingReaders++;
        } finally {
            this.waitingReaders--;
        }
    }

    public synchronized void readUnLock() {
        this.readingReaders--;
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters++;
        try {
            while (writingWriters > 0 || readingReaders > 0) {
                this.wait();
            }
            writingWriters++;
        } finally {
            this.waitingWriters--;
        }
    }

    public synchronized void writeUnLock() {
        this.writingWriters--;
        this.notifyAll();
    }
}
