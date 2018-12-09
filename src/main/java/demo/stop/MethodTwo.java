package demo.stop;

/**
 * 配合interrupt
 */
public class MethodTwo {
    private static class Worker extends Thread {

        @Override
        public void run() {
            while (true) {
                if (Thread.interrupted()) {
                    break;
                }

//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    break;
//                }
            }
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

        worker.interrupt();
    }
}
