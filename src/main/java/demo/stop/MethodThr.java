package demo.stop;

/**
 * 设置守护线程
 */
public class MethodThr {
    private static class ThreadService {
        private Thread executeThread;

        private boolean finished = false;

        public void execute(Runnable task) {
            executeThread = new Thread(() -> {
                Thread runner = new Thread(task);
                runner.setDaemon(true);
                runner.start();
                try {
                    runner.join();
                    finished = true;
                } catch (InterruptedException e) {
                    System.out.println("我被打断了。。。。。");
//                        e.printStackTrace();
                }
            });
            executeThread.start();
        }

        public void shutdown(long mills) {
            long currentTime = System.currentTimeMillis();
            while (!finished) {
                if ((System.currentTimeMillis() - currentTime >= mills)) {
                    System.out.println("任务超时,需要结束。。。");
                    executeThread.interrupt();
                    break;
                }

                //模拟执行时间
                try {
                    executeThread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            finished = false;
        }
    }

    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        long start = System.currentTimeMillis();
        service.execute(() -> {
            while (true) {
                //do something
            }
        });
        service.shutdown(1000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
