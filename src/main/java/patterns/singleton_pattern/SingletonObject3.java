package patterns.singleton_pattern;

/**
 * 该单例模式可以懒加载，也是线程安全,但是性能差，第二次只是读操作不应该同步
 */
public class SingletonObject3 {
    private static SingletonObject3 instance;

    private SingletonObject3() {
        //empty
    }

    public synchronized static SingletonObject3 getInstance() {
        if (instance == null) {
            instance = new SingletonObject3();
        }
        return instance;
    }
}
