package patterns.worker_thread_pattern;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author wangyang
 * @date 2018/12/25 0025
 */
public class ClientThread extends Thread {
    private final Channel channel;
    private static final Random random = new SecureRandom();
    private volatile Boolean terminated = false;

    public ClientThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; !terminated; i++) {
                Request request = new Request(this.getName(), i);
                try {
                    channel.putRequest(request);
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    terminated = true;
                }
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + " is terminated.");
        }
    }

    public void stopThread() {
        terminated = true;
        this.interrupt();
    }
}
