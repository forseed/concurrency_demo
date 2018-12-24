package patterns.thread_per_message_pattern;

/**
 * @author wangyang
 * @date 2018/12/24 0024
 */
public class Main {
    public static void main(String[] args) {
        Host host = new Host();
        host.request(5, 'A');
        host.request(10, 'B');
        host.request(15, 'C');
    }
}
