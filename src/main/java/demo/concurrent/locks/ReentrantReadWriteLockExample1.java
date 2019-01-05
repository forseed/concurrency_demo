package demo.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock demo
 */
public class ReentrantReadWriteLockExample1 {
    static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static final Lock readLock = readWriteLock.readLock();
    static final Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {
        new Thread(ReentrantReadWriteLockExample1::write).start();
        new Thread(ReentrantReadWriteLockExample1::read).start();
    }

    public static void read() {
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " is reading");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " finish reading");
            readLock.unlock();
        }
    }

    public static void write() {
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + " is writing");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " finish writing");
            writeLock.unlock();
        }
    }
}
