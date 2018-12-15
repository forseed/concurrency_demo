package demo.example.threadpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 一个简单的线程池
 */
public class SimpleThreadPool {
    private final int size;
    private final static int DEFAULT_SIZE = 10;
    //任务队列
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    //线程队列
    private final static List<WorkerTask> THREAD_QUENE = new ArrayList<>();

    //序列号
    private static volatile int seq = 0;

    //线程前缀
    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    //线程组
    private final static ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    public SimpleThreadPool() {
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size) {
        this.size = size;
        init();
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            createWorkTask();
        }
    }

    /**
     * 提交任务
     */
    public void submit(Runnable runnable) {
        synchronized (TASK_QUEUE) {
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    /**
     * 创建线程队列
     */
    private void createWorkTask() {
        WorkerTask workerTask = new WorkerTask(GROUP, THREAD_PREFIX + (seq++));
        workerTask.start();
        THREAD_QUENE.add(workerTask);
    }

    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD
    }

    /**
     * 自定义工作线程
     */
    private static class WorkerTask extends Thread {
        private volatile TaskState taskState = TaskState.FREE;

        public WorkerTask(ThreadGroup threadGroup, String name) {
            super(threadGroup, name);
        }

        public TaskState getTaskState() {
            return taskState;
        }

        public void close() {
            this.taskState = TaskState.DEAD;
        }

        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            //退出所有循环
                            break OUTER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();
                }
                if (runnable != null) {
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }
    }

    public static void main(String[] args) {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool();
        IntStream.rangeClosed(1, 40).forEach(i -> {
            simpleThreadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " start...");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end...");
            });
        });
    }
}
