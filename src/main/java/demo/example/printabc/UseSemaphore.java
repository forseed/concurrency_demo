package demo.example.printabc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 有3个线程ABC。按照ABC来运行（A线程输出A，B线程输出B，C线程输出C，以此类推，循环输出）
 */
public class UseSemaphore {

    public static class Print implements Runnable {

        private final String item;
        private final Semaphore self;
        private final Semaphore ctrl;

        public Print(String item, Semaphore self, Semaphore ctrl) {
            this.item = item;
            this.self = self;
            this.ctrl = ctrl;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    self.acquire();
                    System.out.println(item);
                    TimeUnit.SECONDS.sleep(1);
                    ctrl.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Semaphore a = new Semaphore(1);
        Semaphore b = new Semaphore(1);
        Semaphore c = new Semaphore(1);
        a.acquire();
        b.acquire();
        c.acquire();

        new Thread(new Print("A", a, b)).start();
        //测试是否真的顺序输出，将b线程的优先级降低
        Thread thread = new Thread(new Print("B", b, c));
        thread.setPriority(4);
        thread.start();
        new Thread(new Print("C", c, a)).start();

        TimeUnit.SECONDS.sleep(1);
        a.release();
    }
}
