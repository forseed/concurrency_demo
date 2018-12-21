package patterns.balking_pattern;

import java.io.IOException;
import java.util.Random;

/**
 * 模拟修改保存
 */
public class ChangerThread extends Thread {
    private Data data;
    private Random random = new Random();

    public ChangerThread(String name, Data data) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                data.change("No." + i);
                Thread.sleep(random.nextInt(1000));
                data.save();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }
}
