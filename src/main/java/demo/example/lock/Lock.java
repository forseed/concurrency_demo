package demo.example.lock;

import java.util.Collection;

/**
 * 自定义lock
 */
public interface Lock {
    class TimeOutException extends Exception {
        public TimeOutException(String message) {
            super(message);
        }
    }

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeOutException;

    void unlock();

    /**
     * 获取blocked线程集合
     */
    Collection<Thread> getBlockedThread();

    /**
     * 获取blocked线程个数
     */
    int getBlockedSize();
}
