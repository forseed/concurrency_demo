package patterns.worker_thread_pattern;

/**
 * worker thread pattern
 * 一个简单的线程池
 */
public class Main {
    public static void main(String[] args) {
        Channel channel = new Channel(5);
        channel.startWorkers();

        ClientThread alice = new ClientThread("Alice", channel);
        ClientThread demo = new ClientThread("Demo", channel);
        ClientThread seed = new ClientThread("Seed", channel);

        alice.start();
        demo.start();
        seed.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        alice.stopThread();
        demo.stopThread();
        seed.stopThread();

        channel.stopAllWorkers();
    }
}
