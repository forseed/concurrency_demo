package demo.wait.timeout;

/**
 * 测试Time out
 */
public class Main {
    public static void main(String[] args) {
        Host host = new Host(5000);
        new Thread(() -> {
            try {
                System.out.println("begin...");
                host.execute();
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
