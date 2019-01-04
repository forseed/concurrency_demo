package demo.lock;

import java.util.concurrent.TimeUnit;

/**
 * 测试各种lock
 */
public class Main {

    public static void main(String[] args) {
        SemaphoreLock lock = new SemaphoreLock();
//        WaitNotifyLock lock = new WaitNotifyLock();
//        CASLock lock = new CASLock();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is preparing ");
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " get lock");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + " finish work");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }).start();
        }
    }
}
