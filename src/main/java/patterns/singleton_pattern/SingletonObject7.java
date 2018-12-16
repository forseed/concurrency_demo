package patterns.singleton_pattern;

/**
 * 使用枚举
 */
public class SingletonObject7 {

    private SingletonObject7() {
        //empty
    }

    private enum Singleton {
        INSTANCE;

        private final SingletonObject7 instance;

        Singleton() {
            instance = new SingletonObject7();
        }

        public SingletonObject7 getInstance() {
            return instance;
        }
    }

    public static SingletonObject7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
}
