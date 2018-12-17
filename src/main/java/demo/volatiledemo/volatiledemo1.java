package demo.volatiledemo;

/**
 * 初识volatile
 * 不使用volatile修饰 INIT_VALUE，线程修改不可见
 */
public class volatiledemo1 {
    private static int INIT_VALUE = 0;
    //    private static volatile int INIT_VALUE = 0;
    private final static int MAX_LIMIT = 5;

    public static void main(String[] args) {
        new Thread(() -> {
            int localvalue = INIT_VALUE;
            while (localvalue < MAX_LIMIT) {
                if (localvalue != INIT_VALUE) {
                    System.out.printf("the value update to [%d]\n", INIT_VALUE);
                    localvalue = INIT_VALUE;
                }
            }
        }).start();

        //负责更新
        new Thread(() -> {
            int localvalue = INIT_VALUE;
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.printf("update the value to [%d]\n", ++localvalue);
                INIT_VALUE = localvalue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
