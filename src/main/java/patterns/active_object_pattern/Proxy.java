package patterns.active_object_pattern;

/**
 * 将方法调用转换成MethodRequest对象的类
 */
public class Proxy implements ActiveObject {
    private final SchedulerThread scheduler;
    private final Servant servant;

    public Proxy(SchedulerThread scheduler, Servant servant) {
        this.scheduler = scheduler;
        this.servant = servant;
    }

    @Override
    public Result makeString(int count, char fillchar) {
        FutureResult future = new FutureResult();
        scheduler.invoke(new MakeStringRequest(servant, future, count, fillchar));
        return future;
    }

    @Override
    public void displayString(String string) {
        scheduler.invoke(new DisplayStringRequest(servant, string));
    }
}
