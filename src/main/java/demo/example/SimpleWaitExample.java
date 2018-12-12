package demo.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * wait的简单应用
 * 控制线程个数。
 */
public class SimpleWaitExample {
    //容器类
    private final static LinkedList<Control> CONTROLS = new LinkedList<>();
    //同时最多有5个线程可以工作
    private final static int MAX_WORKER = 5;

    public static void main(String[] args) {
        List<Thread> workers = new ArrayList<>();
        //创建10个线程
        Stream.of("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10")
                .map(SimpleWaitExample::createThread)
                .forEach(t -> {
                    t.start();
                    workers.add(t);
                });
        //join线程
        System.out.println(workers.size());
        workers.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Optional.of("All of capture work finished").ifPresent(System.out::println);
    }

    /**
     * 创建线程
     *
     * @param name 线程名称
     * @return Thread
     */
    private static Thread createThread(String name) {
        return new Thread(() -> {
            Optional.of("The worker[" + Thread.currentThread().getName() + "] be created").ifPresent(System.out::println);
            synchronized (CONTROLS) {
                while (CONTROLS.size() > MAX_WORKER) {
                    try {
                        Optional.of("The worker[" + Thread.currentThread().getName() + "] is waiting").ifPresent(System.out::println);
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CONTROLS.addLast(new Control());
            }
            //工作10s
            Optional.of("The worker[" + Thread.currentThread().getName() + "] is working").ifPresent(System.out::println);
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //完成工作,删除队列元素，并唤醒等待线程
            synchronized (CONTROLS) {
                Optional.of("The worker[" + Thread.currentThread().getName() + "] end capture").ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }
        }, name);
    }

    /**
     * 控制类
     */
    private static class Control {

    }
}
