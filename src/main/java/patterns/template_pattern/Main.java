package patterns.template_pattern;

/**
 * 模板方法
 */
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
