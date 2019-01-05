package demo.concurrent.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * ReentrantLock demo
 */
public class ReentrantLockExample1 {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        IntStream.range(0, 2).forEach((i) -> {
            new Thread(ReentrantLockExample1::stage, "T-" + i).start();
        });
    }

    public static void stage() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " first stage start");
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " second stage start");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " second stage finish");
            }
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " first stage finish");
        }
    }
}
