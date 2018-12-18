package patterns.read_write_pattern;

import java.security.SecureRandom;
import java.util.Random;

public class WriterWorker extends Thread {
    private static final Random random = new SecureRandom();

    private final SharedData data;

    private final String filter;

    private int index = 0;

    public WriterWorker(SharedData data, String filter) {
        this.data = data;
        this.filter = filter;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = nextChar();
                data.write(c);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextChar() {
        char c = filter.charAt(index);
        index++;
        if (index >= filter.length()) {
            index = 0;
        }
        return c;
    }
}
