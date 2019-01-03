package demo.concurrent.countdown;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample2 {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            System.out.println("do some initial working..");
            try {
                Thread.sleep(1000);
                latch.await();
                System.out.println("do other working");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            System.out.println("asyn prepare for some data..");
            try {
                Thread.sleep(2000);
                System.out.println("data prepare for done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }).start();
    }
}
