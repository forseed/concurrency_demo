package patterns.producer_consumer_pattern;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author wangyang
 * @date 2018/12/21 0021
 */
public class MakerThread extends Thread {
    private final Table table;
    private final Random random = new SecureRandom();
    private static int id = 0;

    public MakerThread(String name, Table table) {
        super(name);
        this.table = table;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(1000));
                String cake = "[Cake No." + nextId() + " By " + getName() + "]";
                table.put(cake);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized int nextId() {
        return id++;
    }
}
