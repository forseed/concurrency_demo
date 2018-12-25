package patterns.worker_thread_pattern;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author wangyang
 * @date 2018/12/25 0025
 */
public class Request {
    private final String name;
    private final int number;
    private static final Random random = new SecureRandom();

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + " executes " + this);
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "[ Request from " + name + " No." + number + "]";
    }
}
