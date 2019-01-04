package demo.concurrent.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample1 {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            service.submit(new Mechine("m-" + i, semaphore));
        }

        service.shutdown();
    }

    static class Mechine implements Runnable {
        final String name;
        private final Semaphore semaphore;

        Mechine(String name, Semaphore semaphore) {
            this.name = name;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+" wait permit");
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+" get permit");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                System.out.println(Thread.currentThread().getName()+" release permit");
            }
        }
    }

}
