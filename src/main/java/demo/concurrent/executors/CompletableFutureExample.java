package demo.concurrent.executors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import static java.util.stream.Collectors.toList;

public class CompletableFutureExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        testBaseApi();
//        testCompose();
        testCombine();
        Thread.currentThread().join();
    }


    public static void testBaseApi() {
        ExecutorService service = Executors.newFixedThreadPool(2, (r) -> {
            Thread thread = new Thread(r);
            thread.setDaemon(false);
            return thread;
        });

        List<Integer> ids = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = ids.stream().map(i -> CompletableFuture.supplyAsync(() -> query(i), service))
                .map(future -> future.thenApplyAsync(CompletableFutureExample::mulitply))
                .map(CompletableFuture::join).collect(toList());
        System.out.println(collect);
    }

    public static void testCompose() {
        CompletableFuture.supplyAsync(() -> 1).thenCompose(i ->
                CompletableFuture.supplyAsync(() -> i * 10)
        ).thenAccept(System.out::println);
    }

    public static void testCombine() {
        CompletableFuture.supplyAsync(() -> 1)
                .thenCombine(CompletableFuture.supplyAsync(() -> 2), (x, y) -> x + y)
                .thenAccept(System.out::println);
    }

    public static int query(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
            System.out.println(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int mulitply(int i) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i * 10;
    }

}
