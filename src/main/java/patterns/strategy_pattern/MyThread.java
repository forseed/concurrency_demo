package patterns.strategy_pattern;

public class MyThread {
    private MyRunable myRunable;

    public MyThread(MyRunable myRunable) {
        this.myRunable = myRunable;
    }

    public void run() {
        System.out.println("我是MyThread的run方法。。。");
        myRunable.run();
    }


    public void start() {
        System.out.println("调用本地方法。。。。。");
        run();
    }

    public MyRunable getMyRunable() {
        return myRunable;
    }

    public void setMyRunable(MyRunable myRunable) {
        this.myRunable = myRunable;
    }
}
