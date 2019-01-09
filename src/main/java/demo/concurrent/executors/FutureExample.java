package demo.concurrent.executors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;


/**
 * 1.future 没有 回调
 * 2.future 多个任务invokeAll 是同步方法
 */
public class FutureExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> list = Arrays.asList(() -> {
            TimeUnit.SECONDS.sleep(5);
            return 5;
        }, () -> {
            TimeUnit.SECONDS.sleep(10);
            return 10;
        });
        List<Future<Integer>> futures = service.invokeAll(list);
        System.out.println("hello");
        System.out.println(futures.get(0).get());
        System.out.println(futures.get(1).get());

    }
}
