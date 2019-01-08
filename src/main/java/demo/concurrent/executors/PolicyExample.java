package demo.concurrent.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 四大拒绝策略
 */
public class PolicyExample {
    public static void main(String[] args) throws InterruptedException {
//        testPolicy(new ThreadPoolExecutor.AbortPolicy());
//        testPolicy(new ThreadPoolExecutor.DiscardPolicy());
        testPolicy(new ThreadPoolExecutor.DiscardOldestPolicy());
//        testPolicy(new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static void testPolicy(RejectedExecutionHandler policy) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 30,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), Thread::new, policy);

        for (int i = 0; i < 6; i++) {
            executor.execute(new WorkThread(i));
        }

        TimeUnit.SECONDS.sleep(1);

    }

    static class WorkThread implements Runnable {
        private final int no;

        WorkThread(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "[task-" + no + "]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
