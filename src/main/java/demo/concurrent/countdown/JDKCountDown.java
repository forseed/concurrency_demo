package demo.concurrent.countdown;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class JDKCountDown {
    private static final Random random = new SecureRandom();
//    private static CountDownLatch countDownLatch = new CountDownLatch(5);
    private static MyCountDown countDownLatch = new MyCountDown(5);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start..............");
        IntStream.rangeClosed(1, 5).forEach(i -> {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is working..");
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        });
        countDownLatch.await();
        System.out.println("end.............");
    }
}
