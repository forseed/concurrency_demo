package demo.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile不能保证原子性
 */
public class AtomicIntegerTest {
    private static final AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count.getAndIncrement();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count.getAndIncrement();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }
}
