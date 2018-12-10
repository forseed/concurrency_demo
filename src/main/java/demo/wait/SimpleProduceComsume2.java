package demo.wait;

import java.util.stream.Stream;

/**
 * 简单的生产消费模型;
 * 使用notifyAll
 */
public class SimpleProduceComsume2 {
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
                LOCK.notifyAll();
            }
        }
    }

    public void consume() {
        synchronized (LOCK) {
            if (isProduce) {
                System.out.println(Thread.currentThread().getName() + ":C>>>" + i);
                isProduce = false;
                LOCK.notifyAll();
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
        SimpleProduceComsume2 spc = new SimpleProduceComsume2();
        Stream.of("P1", "P2", "P3", "P4").forEach(p -> new Thread(() -> {
            while (true) {
                spc.produce();
            }
        }).start());


        Stream.of("C1", "C2", "C3").forEach((String c) -> new Thread(() -> {
            while (true) {
                spc.consume();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start());

    }
}
