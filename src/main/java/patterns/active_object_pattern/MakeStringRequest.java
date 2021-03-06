package patterns.active_object_pattern;

import patterns.worker_thread_pattern.Request;

/**
 * makeString方法所对应的类，MethodRequest类的子类
 */
public class MakeStringRequest extends MethodRequest {
    private final int count;
    private final char fillchar;

    public MakeStringRequest(Servant servant, FutureResult future, int count, char fillchar) {
        super(servant, future);
        this.count = count;
        this.fillchar = fillchar;
    }

    @Override
    public void execute() {
        Result result = servant.makeString(count, fillchar);
        future.setResult(result);
    }
}
