package patterns.guarded_suspension_pattern;

import java.util.Random;

/**
 * @author wangyang
 * @date 2018/12/19 0019
 */
public class ClientThread extends Thread {
    private final RequestQuene requestQuene;
    private final Random random;


    public ClientThread(RequestQuene requestQuene, String name, long seed) {
        super(name);
        this.requestQuene = requestQuene;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 10000; i++) {
                Request request = new Request("No." + i);
                System.out.println(Thread.currentThread().getName() + " requests " + request);
                requestQuene.putRequest(request);
            }
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
