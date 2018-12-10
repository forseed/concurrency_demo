package patterns.template_pattern;


public abstract class MyThread {
    abstract void run();

    public final void start() {
        System.out.println("调用前处理。。。。");
        run();
        System.out.println("调用后处理。。。。");
    }
}
