package demo.deadlock;

/**
 * 一个简单的死锁案例
 */
public class SimpleDeadLock {
    private static final String FILEA = "A";
    private static final String FILEB = "B";

    public void m1() {
        while (true) {
            synchronized (FILEA) {
                System.out.println("m1。。。。fileA");
                synchronized (FILEB) {
                    System.out.println("m1。。。。fileB");
                }
            }
        }
    }

    public void m2() {
        while (true) {
            synchronized (FILEB) {
                System.out.println("m2。。。。fileB");
                synchronized (FILEA) {
                    System.out.println("m2。。。。fileA");
                }
            }
        }
    }

    public static void main(String[] args) {
        SimpleDeadLock deadLock = new SimpleDeadLock();
        new Thread(deadLock::m1).start();
        new Thread(deadLock::m2).start();
    }
}
