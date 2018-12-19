package patterns.guarded_suspension_pattern;

/**
 * @author wangyang
 * @date 2018/12/19 0019
 */
public class Main {
    public static void main(String[] args) {
        RequestQuene requestQuene = new RequestQuene();
        new ClientThread(requestQuene,"client",999999).start();
        new ServerThread(requestQuene,"server",654321).start();
    }
}
