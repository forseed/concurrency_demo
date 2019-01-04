package demo.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample1 {
    public static void main(String[] args) {
        final CyclicBarrier barrier =
                new CyclicBarrier(2, () -> System.out.println("alice and anna go home"));

        new Thread(() -> {
            try {
                System.out.println("alice is woring");
                Thread.sleep(3000);
                barrier.await();
                System.out.println("alice go home");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println("anna is working");
                Thread.sleep(5000);
                barrier.await();
                System.out.println("anna go home");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
