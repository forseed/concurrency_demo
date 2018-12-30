package demo.concurrent.countdown;

public class MyCountDown {
    private final int total;

    private int count;

    public MyCountDown(int total) {
        this.total = total;
    }

    public synchronized void countDown() {
        count++;
        notifyAll();
    }

    public synchronized void await() {
        while (total != count) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
