package patterns.thread_per_message_pattern;

/**
 * @author wangyang
 * @date 2018/12/24 0024
 */
public class Host {
    private final Helper helper = new Helper();

    public void request(int count, char c) {
        System.out.printf("request (%d,%s) begin\n", count, c);
        new Thread(() -> helper.handle(count, c)).start();
        System.out.printf("request (%d,%s) end\n", count, c);
    }
}
