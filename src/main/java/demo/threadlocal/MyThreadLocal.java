package demo.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单实现ThreadLocal
 */
public class MyThreadLocal<T> {
    private final Map<Thread, T> storage = new HashMap<>();

    public synchronized void set(T t) {
        storage.put(Thread.currentThread(), t);
    }

    public synchronized T get() {
        Thread key = Thread.currentThread();
        T val = storage.get(key);
        if (val == null) {
            return initialValue();
        }
        return val;
    }

    public T initialValue() {
        return null;
    }
}
