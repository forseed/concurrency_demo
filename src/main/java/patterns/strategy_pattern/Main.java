package patterns.strategy_pattern;

/**
 * 策略模式
 */
public class Main {
    public static void main(String[] args) {
//        new MyThread(() -> System.out.println("runable。。。。。")).start();
        new MyThread(new MyRunable() {
            @Override
            public void run() {
                System.out.println("run。。。。。");
            }
        }).start();
    }
}
