package patterns.worker_thread_pattern;

/**
 * worker thread pattern
 * 一个简单的线程池
 */
public class Main {
    public static void main(String[] args) {
        Channel channel = new Channel(5);
        channel.startWorkers();

        new ClientThread("Alice", channel).start();
        new ClientThread("Demo", channel).start();
        new ClientThread("Seed", channel).start();
    }
}
