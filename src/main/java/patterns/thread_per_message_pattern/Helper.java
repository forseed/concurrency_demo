package patterns.thread_per_message_pattern;

/**
 * @author wangyang
 * @date 2018/12/24 0024
 */
public class Helper {

    public void handle(int count, char c) {
        System.out.printf("handle (%d,%s) begin\n", count, c);
        for (int i = 0; i < count; i++) {
            try {
                Thread.sleep(100);
                System.out.print(c);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("");
        System.out.printf("handle (%d,%s) end\n", count, c);
    }
}
