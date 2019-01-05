package demo.concurrent.condition;


import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition demo
 */
public class ConditionExample {
    private final Lock lock = new ReentrantLock();
    private final Condition PRODUCE_CON = lock.newCondition();
    private final Condition CONSUME_CON = lock.newCondition();
    private final int MAX_COUNT = 100;
    private final LinkedList<String> list = new LinkedList<>();
    private static AtomicInteger index = new AtomicInteger();

    public void produce(int id) {
        try {
            lock.lock();
            while (list.size() >= MAX_COUNT) {
                PRODUCE_CON.await();
            }
            System.out.println("ppppp->" + id);
            list.addLast("item-" + id);
            CONSUME_CON.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        try {
            lock.lock();
            while (list.size() <= 0) {
                CONSUME_CON.await();
            }
            String item = list.removeFirst();
            System.out.println("ccccc->" + item);
            PRODUCE_CON.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionExample conditionExample = new ConditionExample();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                for (; ; ) {
                    conditionExample.produce(index.getAndIncrement());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (; ; ) {
                    conditionExample.consume();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
