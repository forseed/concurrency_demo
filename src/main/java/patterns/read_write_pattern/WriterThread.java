package patterns.read_write_pattern;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author wangyang
 * @date 2018/12/25 0025
 */
public class WriterThread extends Thread {
    private final static Random random = new SecureRandom();
    private final Data data;
    private final String filler;
    private int index = 0;

    WriterThread(Data data, String filler) {
        this.data = data;
        this.filler = filler;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = this.nextchar();
                data.write(c);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextchar() {
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length()) {
            index = 0;
        }
        return c;
    }
}
