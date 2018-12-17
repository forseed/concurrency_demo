package demo.example.observer_thread;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1", "2", "3"));
    }
}
