package demo.threadgroup;

import java.util.Arrays;

/**
 * 线程组相关api
 */
public class ThreadGroupCreate {
    public static void main(String[] args) {
        //use the name
        ThreadGroup tg1 = new ThreadGroup("TG1");
        new Thread(tg1, "t1") {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println(getThreadGroup().getName());
                        System.out.println(getThreadGroup().getParent());
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }.start();

        //use the parent and group name
        ThreadGroup tg2 = new ThreadGroup(tg1, "TG2");
        new Thread(tg2, "t2") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread[] threads = new Thread[tg1.activeCount()];
                        tg1.enumerate(threads);
                        Arrays.asList(threads).forEach(System.out::println);
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }.start();

        //主线程
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        Thread[] ts = new Thread[mainGroup.activeCount()];
        mainGroup.enumerate(ts, true);
        Arrays.asList(ts).forEach(x -> System.out.println(">>>" + x));


        //测试打断
        mainGroup.interrupt();
    }

}
