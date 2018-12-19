package patterns.guarded_suspension_pattern;

/**
 * @author wangyang
 * @date 2018/12/19 0019
 */
public class Request {
    private final String name;

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[Request " + name + "]";
    }
}
