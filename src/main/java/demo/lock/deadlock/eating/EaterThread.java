package demo.lock.deadlock.eating;

/**
 * @author wangyang
 * @date 2018/12/19 0019
 */
public class EaterThread extends Thread {
    private final String name;
    private final Tools lefthand;
    private final Tools righthand;


    public EaterThread(String name, Tools lefthand, Tools righthand) {
        this.name = name;
        this.lefthand = lefthand;
        this.righthand = righthand;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (lefthand) {
                System.out.println(name + " is using " + lefthand);
                synchronized (righthand) {
                    System.out.println(name + " is using " + righthand);
                }
            }
        }
    }
}
