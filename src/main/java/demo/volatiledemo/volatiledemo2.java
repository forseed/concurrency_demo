package demo.volatiledemo;

/**
 * 测试线程是不是每次都从cache中获取数据
 * 并不是，当jvm认为有写操作仍然会更新到主存
 */
public class volatiledemo2 {
    private static int INIT_VALUE = 0;

    private final static int MAX_VALUE = 20;

    public static void main(String[] args) {
        new Thread(() -> {
            while (INIT_VALUE < MAX_VALUE) {
                System.out.printf("T1 the value update to [%d]\n", ++INIT_VALUE);
            }
        }).start();


        new Thread(() -> {
            while (INIT_VALUE < MAX_VALUE) {
                System.out.printf("T2 the value update to [%d]\n", ++INIT_VALUE);
            }
        }).start();
    }
}
