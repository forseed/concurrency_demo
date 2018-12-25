package patterns.producer_consumer_pattern;

import java.util.Random;

/**
 * @author wangyang
 * @date 2018/12/21 0021
 */
public class EaterThread extends Thread {
    private final Table table;
    private final Random random = new Random();

    public EaterThread(String name, Table table) {
        super(name);
        this.table = table;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(1000));
                table.take();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
