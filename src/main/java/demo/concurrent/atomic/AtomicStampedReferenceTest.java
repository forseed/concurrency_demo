package demo.concurrent.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {
    private static AtomicStampedReference<Integer> atomicReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                boolean f1 = atomicReference.compareAndSet(100, 101, atomicReference.getStamp(), atomicReference.getStamp() + 1);
                System.out.println("f1:" + f1 + "-- stamp:" + atomicReference.getStamp());
                boolean f2 = atomicReference.compareAndSet(101, 100, atomicReference.getStamp(), atomicReference.getStamp() + 1);
                System.out.println("f2:" + f2 + "-- stamp:" + atomicReference.getStamp());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                int stam = atomicReference.getStamp();
                System.out.println("before stamp:" + atomicReference.getStamp());
                TimeUnit.SECONDS.sleep(2);
                boolean f3 = atomicReference.compareAndSet(100, 101, stam, stam + 1);
                System.out.println("f3:" + f3 + "-- stamp:" + atomicReference.getStamp());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
