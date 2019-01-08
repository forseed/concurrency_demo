package demo.concurrent.executors;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 创建线程池的多种方式
 */
public class ThreadPoolExample {
    public static void main(String[] args) throws InterruptedException {
//        useThreadPoolExecutor();
//        useCachedThreadPool();
//        useFixedThreadPool();
//        useSingleThreadExecutor();
        useWorkStealingPool();
    }

    /**
     * 使用构造器创建，包含7个参数
     * int corePoolSize,
     * int maximumPoolSize,
     * long keepAliveTime,
     * TimeUnit unit,
     * BlockingQueue<Runnable> workQueue,
     * ThreadFactory threadFactory,
     * RejectedExecutionHandler handler
     */
    private static void useThreadPoolExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 30,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), Thread::new, new ThreadPoolExecutor.AbortPolicy());

        executor.execute(() -> System.out.println("hello"));
        executor.execute(() -> System.out.println("hello"));
        executor.execute(() -> System.out.println("hello"));
        executor.execute(() -> System.out.println("hello"));
    }

    /**
     * new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
     */
    private static void useCachedThreadPool() throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        //未提交任务
//        System.out.println(((ThreadPoolExecutor) service).getActiveCount());

        //提交一个任务
//        service.execute(() -> System.out.println("hello"));
//        System.out.println(((ThreadPoolExecutor) service).getActiveCount());

        //提交100个任务
        IntStream.range(0, 100).forEach(i -> service.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("cachedThreadPool:" + Thread.currentThread().getName() + "[" + i + "]");
        }));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(((ThreadPoolExecutor) service).getActiveCount());
    }

    /**
     * new ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>()
     */
    private static void useFixedThreadPool() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        //提交100个任务
        IntStream.range(0, 20).forEach(i -> service.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("fixedThreadPool:" + Thread.currentThread().getName() + "[" + i + "]");
        }));
        TimeUnit.SECONDS.sleep(1);
        service.shutdown();
        System.out.println(((ThreadPoolExecutor) service).getActiveCount());
    }

    /**
     * new FinalizableDelegatedExecutorService
     * (new ThreadPoolExecutor(1, 1,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>())
     */
    private static void useSingleThreadExecutor() throws InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        //提交100个任务
        IntStream.range(0, 10).forEach(i -> service.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("SingleThreadExecutor:" + Thread.currentThread().getName() + "[" + i + "]");
        }));
        TimeUnit.SECONDS.sleep(1);
        service.shutdown();
    }

    /**
     * WorkStealingPool
     */
    private static void useWorkStealingPool() throws InterruptedException {
        ExecutorService service = Executors.newWorkStealingPool();
        List<Callable<String>> callableList = IntStream.range(0, 20).boxed().map(i -> (Callable<String>) () -> {
            System.out.println(Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
            return "Task-" + i;
        }).collect(Collectors.toList());
        service.invokeAll(callableList).forEach(future -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
