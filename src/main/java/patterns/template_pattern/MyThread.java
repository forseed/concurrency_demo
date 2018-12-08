package patterns.template_pattern;

/**
 * 通过start方法调用run方法,类似模板方法
 */
public abstract class MyThread {
    abstract void run();

    public final void start() {
        System.out.println("调用前处理。。。。");
        run();
        System.out.println("调用后处理。。。。");
    }
}
