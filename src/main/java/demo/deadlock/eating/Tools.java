package demo.deadlock.eating;

/**
 * @author wangyang
 * @date 2018/12/19 0019
 */
public class Tools {
    private final String name;

    public Tools(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
