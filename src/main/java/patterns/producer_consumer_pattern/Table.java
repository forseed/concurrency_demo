package patterns.producer_consumer_pattern;

import java.util.LinkedList;

/**
 * @author wangyang
 * @date 2018/12/21 0021
 */
public class Table {
    private final int size;
    private LinkedList<String> buffer = new LinkedList<>();

    public Table(int size) {
        this.size = size;
    }


    public synchronized void put(String cake) throws InterruptedException {
        while (buffer.size() >= size) {
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + " puts " + cake);
        buffer.addLast(cake);
        this.notifyAll();
    }


    public synchronized String take() throws InterruptedException {
        while (buffer.size() <= 0) {
            this.wait();
        }

        String cake = buffer.removeFirst();
        System.out.println(Thread.currentThread().getName() + " takes " + cake);
        this.notifyAll();
        return cake;
    }

}
