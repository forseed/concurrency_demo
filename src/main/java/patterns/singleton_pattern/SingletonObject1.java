package patterns.singleton_pattern;

/**
 * 该单例模式线程安全但是不能懒加载
 */
public class SingletonObject1 {
    private static final SingletonObject1 instance = new SingletonObject1();

    private SingletonObject1() {
        //empty
    }

    public static SingletonObject1 getInstance() {
        return SingletonObject1.instance;
    }
}
