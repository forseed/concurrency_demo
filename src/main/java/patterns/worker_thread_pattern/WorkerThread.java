package patterns.worker_thread_pattern;

/**
 * @author wangyang
 * @date 2018/12/25 0025
 */
public class WorkerThread extends Thread {
    private final Channel channel;

    private volatile boolean terminated = false;

    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            while (!terminated) {
                try {
                    Request request = channel.takeRequest();
                    request.execute();
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
