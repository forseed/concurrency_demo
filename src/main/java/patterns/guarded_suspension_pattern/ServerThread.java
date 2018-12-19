package patterns.guarded_suspension_pattern;

import java.util.Random;

/**
 * @author wangyang
 * @date 2018/12/19 0019
 */
public class ServerThread extends Thread {
    private final RequestQuene requestQuene;
    private final Random random;


    public ServerThread(RequestQuene requestQuene, String name, long seed) {
        super(name);
        this.requestQuene = requestQuene;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 10000; i++) {
                Request request = requestQuene.getRequest();
                System.out.println(Thread.currentThread().getName() + " handles " + request);
            }
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
