package demo.concurrent.countdown;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample1 {

    private static Random random = new SecureRandom();

    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    private static CountDownLatch latch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        //1.query
        int[] data = query();
        //2.execute
        for (int i = 0; i < data.length; i++) {
            executor.execute(new SimpleRunnable(data, i, latch));
        }
        //3.result
        latch.await();
        executor.shutdown();
        System.out.println("all works finish done.");
    }

    static class SimpleRunnable implements Runnable {

        private final int[] data;
        private final int index;
        private final CountDownLatch latch;

        public SimpleRunnable(int[] data, int index, CountDownLatch latch) {
            this.data = data;
            this.index = index;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int value = data[index];
            if (value % 2 == 0) {
                data[index] = value * 2;
            } else {
                data[index] = value * 10;
            }
            System.out.println(Thread.currentThread().getName() + " finished:" + index);
            latch.countDown();
        }
    }

    private static int[] query() {
        return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    }
}
