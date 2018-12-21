package patterns.balking_pattern;

/**
 * @author wangyang
 * @date 2018/12/21 0021
 */
public class Main {
    public static void main(String[] args) {
        Data data = new Data("data.txt", "(empty)");
        new ChangerThread("ChangerThread", data).start();
        new SaverThread("SaverThread", data).start();
    }
}
