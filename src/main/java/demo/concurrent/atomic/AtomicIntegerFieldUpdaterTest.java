package demo.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {
    private volatile int i;
    AtomicIntegerFieldUpdater updater
            = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterTest.class, "i");

    public void update(int newValue) {
        updater.compareAndSet(this, i, newValue);
    }

    public int get() {
        return i;
    }

    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterTest updaterTest = new AtomicIntegerFieldUpdaterTest();
        updaterTest.update(25);
        System.out.println(updaterTest.get());
    }
}
