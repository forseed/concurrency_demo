package demo.concurrent.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanTest {
    private static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (flag.get()) {
                try {
                    Thread.sleep(500);
                    System.out.println("i am working...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(5000);
        flag.set(false);
    }
}
