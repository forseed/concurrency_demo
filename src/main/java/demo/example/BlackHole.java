package demo.example;

import java.util.Objects;

/**
 * 编写magic方法，使程序只输出 AAA,BBB，不能输出CCC,程序未终止
 */
public class BlackHole {
    public static void enter(Object obj) {
        System.out.println("AAA");
        magic(obj);
        System.out.println("BBB");
        synchronized (obj) {
            System.out.println("CCC (Nerver Reached Here)");
        }
    }

    /**
     * 1.主线程正常收回了
     * 2.开启了一个新的线程
     * 3.新线程持有obj锁，并且在主线程收回之前
     */
    private static void magic(Object obj) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                synchronized (obj) {
                    synchronized (this) {
                        this.setName("lock");
                        this.notifyAll();
                    }

                    while (true) {

                    }
                }
            }
        };
        synchronized (thread) {
            thread.setName("");
            thread.start();
            while (Objects.equals(thread.getName(), "")) {
                try {
                    thread.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("begin");
        Object obj = new Object();
        BlackHole.enter(obj);
        System.out.println("end");
    }
}
