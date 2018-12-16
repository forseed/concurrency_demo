package demo.wait;

import java.util.stream.IntStream;

/**
 * 测试notify的唤醒策略
 */
public class NotifySeq {
    private final static Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        IntStream.rangeClosed(1, 10).forEach(i ->
                new Thread("Thread-" + i) {
                    @Override
                    public void run() {
                        synchronized (LOCK) {
                            try {
                                System.out.println(Thread.currentThread().getName() + " is waiting ...");
                                LOCK.wait();
                                System.out.println(Thread.currentThread().getName() + " has be notified ...");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start()
        );

        Thread.sleep(1000);
        System.out.println("================================");
        //notify
        IntStream.rangeClosed(1, 10).forEach(i -> {
            synchronized (LOCK) {
                LOCK.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
