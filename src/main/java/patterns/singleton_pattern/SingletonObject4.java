package patterns.singleton_pattern;

/**
 * 该单例模式可以懒加载，也是线程安全,但是可能会引起空指针异常，由于重排序的影响
 */
public class SingletonObject4 {
    private static SingletonObject4 instance;

    private SingletonObject4() {
        //empty
    }

    public static SingletonObject4 getInstance() {
        if (instance == null) {
            synchronized (SingletonObject4.class) {
                if (instance == null) {
                    instance = new SingletonObject4();
                }
            }
        }
        return instance;
    }
}
