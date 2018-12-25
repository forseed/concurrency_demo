package patterns.worker_thread_pattern;

/**
 * @author wangyang
 * @date 2018/12/25 0025
 */
public class WorkerThread extends Thread {
    private final Channel channel;


    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            channel.takeRequest().execute();
        }
    }
}
