package demo.example.stop;

/**
 * 定义标记
 */
public class MethodOne {
    private static class Worker extends Thread {
        private volatile boolean start = true;

        @Override
        public void run() {
            while (start) {
                // do something
            }
        }

        public void shotdown() {
            start = false;
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        //主线程等待3s后中断子线程
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.shotdown();
    }
}
