package demo.exception;

/**
 * 模板方法处理
 */
public class ThreadExceptionExample2 {
    public static void main(String[] args) {
        Thread thread = new Thread(new ExceptionHandler() {
            @Override
            protected void doFinally() {
                System.out.println("do finally");
            }

            @Override
            protected void doException(Throwable throwable) {
                System.out.println("do exception");
                throwable.printStackTrace();
            }

            @Override
            protected void doInit() {
                System.out.println("do init");
                int i = 1 / 0;
            }
        });
        thread.start();
    }

    static abstract class ExceptionHandler implements Runnable {
        @Override
        public void run() {
            try {
                doInit();
            } catch (Throwable throwable) {
                doException(throwable);
            } finally {
                doFinally();
            }
        }

        protected abstract void doFinally();

        protected abstract void doException(Throwable throwable);

        protected abstract void doInit();
    }
}
