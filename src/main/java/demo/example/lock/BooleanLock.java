package demo.example.lock;

import java.util.*;

/**
 * Lock的简单实现类
 * (使用wait/notifyAll和synchronized实现)
 */
public class BooleanLock implements Lock {
    /**
     * 是否持有锁
     */
    private boolean isLock;

    /**
     * blocked线程集合
     */
    private Collection<Thread> blockedThreadCollection = new HashSet<>();

    /**
     * 记录当前线程
     */
    private Thread thread;

    public BooleanLock() {
        this.isLock = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (isLock) {
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        thread = Thread.currentThread();
        blockedThreadCollection.remove(Thread.currentThread());
        isLock = true;
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if (mills <= 0) {
            lock();
        }

        long hasRemaining = mills;
        long endTime = System.currentTimeMillis() + mills;
        while (isLock) {
            if (hasRemaining <= 0) {
                blockedThreadCollection.remove(Thread.currentThread());
                throw new TimeOutException(Thread.currentThread().getName() + " time out...");
            }
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            hasRemaining = endTime - System.currentTimeMillis();
        }

        thread = Thread.currentThread();
        blockedThreadCollection.remove(Thread.currentThread());
        isLock = true;
    }

    /**
     * 只有当前线程才可以释放锁
     */
    @Override
    public synchronized void unlock() {
        if (Thread.currentThread() == thread) {
            isLock = false;
            Optional.of(Thread.currentThread().getName() + " release the lock monitor...")
                    .ifPresent(System.out::println);
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }
}
