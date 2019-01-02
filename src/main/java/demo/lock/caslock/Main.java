package demo.lock.caslock;

public class Main {

    private final static CASLock casLock = new CASLock();

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    doSomething();
                } catch (LockException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }

    private static void doSomething() throws LockException {
        casLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" is working..");
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            casLock.unlock();
        }
    }
}
