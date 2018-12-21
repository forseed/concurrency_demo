package demo.wait.timeout;

/**
 * Host
 */
public class Host {
    private final long timeout;
    private boolean ready = false;

    public Host(long timeout) {
        this.timeout = timeout;
    }

    //更改状态
    public synchronized void setExecutable(boolean on) {
        ready = on;
        this.notifyAll();
    }

    //评断状态后执行它
    public synchronized void execute() throws InterruptedException {
        long start = System.currentTimeMillis();
        long end = start + timeout;
        while (!ready) {
            long rest = end - System.currentTimeMillis();
            if (rest <= 0) {
                throw new TimeoutException("time out");
            }
            wait(rest);
        }
        doExecute();
    }

    private void doExecute() {
        System.out.println(Thread.currentThread().getName() + "calls doExecute...");
    }
}
