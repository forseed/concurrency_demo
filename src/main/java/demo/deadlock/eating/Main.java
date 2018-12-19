package demo.deadlock.eating;

/**
 * @author wangyang
 * @date 2018/12/19 0019
 */
public class Main {
    public static void main(String[] args) {
        Tools fork = new Tools("fork");
        Tools spoon = new Tools("spoon");

        new EaterThread("alice", fork, spoon).start();
        new EaterThread("demo", spoon, fork).start();
    }
}
