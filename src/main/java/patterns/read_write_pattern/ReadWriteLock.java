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
    //优先字段
    private boolean preferWriter = true;


    public synchronized void readLock() {

    }

    public synchronized void readUnLock() {

    }
}
