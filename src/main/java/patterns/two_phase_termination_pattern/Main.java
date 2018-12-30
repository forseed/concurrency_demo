package patterns.two_phase_termination_pattern;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main: start");
        CountupThread t = new CountupThread();
        t.start();
        Thread.sleep(10000);
        System.out.println("main: shutdownRequest");
        t.shutdownRequest();
        t.join();
        System.out.println("main: end");
    }
}
