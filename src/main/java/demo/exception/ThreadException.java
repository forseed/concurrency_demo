package demo.exception;

/**
 * 运行时异常是无法在其他线程中捕获到的
 * 可以使用setUncaughtExceptionHandler
 */
public class ThreadException {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3_000);
                int i = 1 / 0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        //使用api
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(t);
            System.out.println(e);
        });
    }
}
