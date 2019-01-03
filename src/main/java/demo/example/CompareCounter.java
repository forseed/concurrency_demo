package demo.example;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 几种同步方式性能比较
 */
public class CompareCounter {

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException {
        ExecutorService service = Executors.newFixedThreadPool(1000);
        /**
         * unsafe counter:
         *      Counter result:96028028
         *      Time spend:212
         *
         * synchronized counter
         *      Counter result:100000000
         *      Time spend:3813
         *
         * lock counter
         *      Counter result:100000000
         *      Time spend:2947
         *
         *  atomic counter
         *      Counter result:100000000
         *      Time spend:3683
         *
         *  unsafe counter
         *      Counter result:100000000
         *      Time spend:11011
         */

//        Counter counter = new UnsafeCounter();
//        Counter counter = new SynCounter();
//        Counter counter = new LockCounter();
//        Counter counter = new AtomicCounter();
        Counter counter = new CasCounter();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            service.submit(new CountRunable(counter, 100000));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);
        long end = System.currentTimeMillis();
        System.out.println("Counter result:" + counter.getCounter());
        System.out.println("Time spend:" + (end - start));

    }

    /**
     * get unsafe
     */
    private static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    interface Counter {
        void increment();

        long getCounter();
    }

    private static class CountRunable implements Runnable {
        private final Counter counter;
        private final int num;

        public CountRunable(Counter counter, int num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            for (int i = 0; i < num; i++) {
                counter.increment();
            }
        }
    }

    //1.unsafe counter
    static class UnsafeCounter implements Counter {
        private long counter = 0;

        @Override
        public void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    //2.synchronized counter
    static class SynCounter implements Counter {
        private long counter = 0;

        @Override
        public synchronized void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    //3.lock counter
    static class LockCounter implements Counter {
        private long counter = 0;
        private final Lock lock = new ReentrantLock();

        @Override
        public void increment() {
            lock.lock();
            try {
                counter++;
            } finally {
                lock.unlock();
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    //4.atomic counter
    static class AtomicCounter implements Counter {
        private AtomicLong counter = new AtomicLong();

        @Override
        public void increment() {
            counter.incrementAndGet();
        }

        @Override
        public long getCounter() {
            return counter.get();
        }
    }

    //5.cas counter
    static class CasCounter implements Counter {
        private volatile long counter = 0;
        private final Unsafe unsafe;
        private long offset;

        CasCounter() throws NoSuchFieldException {
            unsafe = getUnsafe();
            offset = unsafe.objectFieldOffset(CasCounter.class.getDeclaredField("counter"));
        }

        @Override
        public void increment() {
            while (!unsafe.compareAndSwapLong(this, offset, counter, counter + 1)) {

            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }
}



