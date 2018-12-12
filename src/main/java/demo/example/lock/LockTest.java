package demo.example.lock;

import java.util.Optional;
import java.util.stream.Stream;

public class LockTest {
    public static void main(String[] args) throws InterruptedException {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1", "T2", "T3", "T4").forEach(name -> new Thread(() -> {
            try {
//                booleanLock.lock();
                booleanLock.lock(10_000);
                Optional.of(Thread.currentThread().getName() + " have the lock monitor")
                        .ifPresent(System.out::println);
                work();
            } catch (InterruptedException | Lock.TimeOutException e) {
                Optional.of(e.getMessage())
                        .ifPresent(System.out::println);
            } finally {
                booleanLock.unlock();
            }
        }, name).start());

        //测试其他线程释放锁
        Thread.sleep(100);
        booleanLock.unlock();
    }

    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName() + " is working...")
                .ifPresent(System.out::println);
        Thread.sleep(5_000);
    }
}
