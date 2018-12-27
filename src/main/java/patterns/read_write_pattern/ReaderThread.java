package patterns.read_write_pattern;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author wangyang
 * @date 2018/12/25 0025
 */
public class ReaderThread extends Thread {
    private final static Random random = new SecureRandom();
    private final Data data;

    public ReaderThread(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] readbuf = data.read();
                System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readbuf));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
