package patterns.active_object_pattern;

/**
 * 产生主动对象的类
 */
public class ActiveObjectFacory {
    public static ActiveObject createActiveObject() {
        //实际处理的类
        Servant servant = new Servant();

        //用来依次存放MethodRequest的类
        ActivationQueue queue = new ActivationQueue();

        //用来执行MethodRequest对象的类
        SchedulerThread scheduler = new SchedulerThread(queue);

        //将方法调用转换成MethodRequest对象的类
        Proxy proxy = new Proxy(scheduler, servant);

        scheduler.start();
        return proxy;
    }
}
