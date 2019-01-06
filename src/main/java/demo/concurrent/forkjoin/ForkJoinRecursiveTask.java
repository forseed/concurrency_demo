package demo.concurrent.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * RecursiveTask 有返回值
 * RecursiveAction 无返回值
 */
public class ForkJoinRecursiveTask {

    private final static int MAX_THRESHLOD = 3;


    private static class CalculateRecursiveTask extends RecursiveTask<Integer> {

        private final int start;
        private final int end;

        private CalculateRecursiveTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            System.out.println("compute...");
            if (end - start <= MAX_THRESHLOD) {
                return IntStream.rangeClosed(start, end).sum();
            }
            int mid = (start + end) / 2;
            CalculateRecursiveTask leftTask = new CalculateRecursiveTask(start, mid);
            CalculateRecursiveTask rightTask = new CalculateRecursiveTask(mid + 1, end);
            leftTask.fork();
            rightTask.fork();
            return leftTask.join() + rightTask.join();
        }

        public static void main(String[] args) throws ExecutionException, InterruptedException {
            final ForkJoinPool forkJoinPool = new ForkJoinPool();
            ForkJoinTask<Integer> future = forkJoinPool.submit(new CalculateRecursiveTask(0, 10));
            Integer sum = future.get();
            System.out.println(sum);
        }
    }
}
