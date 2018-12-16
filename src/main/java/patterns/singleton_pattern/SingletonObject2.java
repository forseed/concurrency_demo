package patterns.singleton_pattern;

/**
 * 该单例模式可以懒加载，但是非线程安全
 */
public class SingletonObject2 {
    private static SingletonObject2 instance;

    private SingletonObject2() {
        //empty
    }

    public static SingletonObject2 getInstance() {
        if (instance == null) {
            instance = new SingletonObject2();
        }
        return instance;
    }
}
