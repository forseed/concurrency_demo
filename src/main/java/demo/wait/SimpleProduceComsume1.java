package demo.wait;

import java.util.stream.Stream;

/**
 * 简单的生产消费模型;
 * 该事例不适用于多生产者多消费者，会产生假死现象，因为可能所有的线程都是wait状态
 * 解决方法见SimpleProduceComsume2
 */
public class SimpleProduceComsume1 {
    private final Object LOCK = new Object();
    private volatile Boolean isProduce = false;
    private int i = 0;

    public void produce() {
        synchronized (LOCK) {
            if (isProduce) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println(Thread.currentThread().getName() + ":P>>>" + i);
                isProduce = true;
                LOCK.notify();
            }
        }
    }

    public void consume() {
        synchronized (LOCK) {
            if (isProduce) {
                System.out.println(Thread.currentThread().getName() + ":C>>>" + i);
                isProduce = false;
                LOCK.notify();
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SimpleProduceComsume1 spc = new SimpleProduceComsume1();
        Stream.of("P1", "P2").forEach(p -> new Thread(() -> {
            while (true) {
                spc.produce();
            }
        }).start());


        Stream.of("C1", "C2").forEach((String c) -> new Thread(() -> {
            while (true) {
                spc.consume();
            }
        }).start());

    }
}
