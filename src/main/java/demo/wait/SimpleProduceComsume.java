package demo.wait;

/**
 * 简单的生产消费模型
 */
public class SimpleProduceComsume {
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
                System.out.println("P>>>" + i);
                isProduce = true;
                LOCK.notify();
            }
        }
    }

    public void consume() {
        synchronized (LOCK) {
            if (isProduce) {
                System.out.println("C>>>" + i);
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
        SimpleProduceComsume spc = new SimpleProduceComsume();
        new Thread(() -> {
            while (true) {
                spc.produce();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                spc.consume();
            }
        }).start();
    }
}
