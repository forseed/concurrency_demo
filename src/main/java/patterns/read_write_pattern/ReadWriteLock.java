package patterns.read_write_pattern;

/**
 * @author wangyang
 * @date 2018/12/25 0025
 */
public final class ReadWriteLock {
    //正在读取的线程数量
    private int readingReaders = 0;
    //等待写入的线程数量
    private int waitingWriters = 0;
    //正在写入的线程数量
    private int writingWriters = 0;
    //写入优先
    private boolean preferWriter = true;


    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
            this.wait();
        }
        readingReaders++;
    }

    public synchronized void readUnLock() {
        readingReaders--;
        preferWriter = true;
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        waitingWriters++;
        try {
            while (writingWriters > 0 || readingReaders > 0) {
                this.wait();
            }
        } finally {
            waitingWriters--;
        }
        writingWriters++;
    }

    public synchronized void writeUnLock() {
        writingWriters--;
        preferWriter = false;
        this.notifyAll();
    }
}
