package patterns.singleton_pattern;

/**
 * 该单例模式可以懒加载，也是线程安全,使用volatile防止重排序
 */
public class SingletonObject5 {
    private static volatile SingletonObject5 instance;

    private SingletonObject5() {
        //empty
    }

    public static SingletonObject5 getInstance() {
        if (instance == null) {
            synchronized (SingletonObject5.class) {
                if (instance == null) {
                    instance = new SingletonObject5();
                }
            }
        }
        return instance;
    }
}
