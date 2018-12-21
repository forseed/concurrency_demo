package patterns.balking_pattern;

import java.io.IOException;

/**
 * 定期保存
 */
public class SaverThread extends Thread {
    private Data data;

    public SaverThread(String name, Data data) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                data.save();
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
