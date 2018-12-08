package patterns.template_pattern;

public class Main extends MyThread {
    @Override
    void run() {
        System.out.println("run。。。。。");
    }

    public static void main(String[] args) {
        new Main().start();
        new Main().run();
    }
}
