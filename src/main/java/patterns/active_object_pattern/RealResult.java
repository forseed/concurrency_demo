package patterns.active_object_pattern;

/**
 * 表示实际执行结果的类
 */
public class RealResult extends Result {
    private final Object resultValue;

    public RealResult(Object resultValue) {
        this.resultValue = resultValue;
    }

    @Override
    public Object getResultValue() {
        return resultValue;
    }
}
