package patterns.active_object_pattern;

/**
 * Future Pattern中用来显示执行结果的抽象类
 */
public class FutureResult extends Result {
    private Result result;
    private boolean ready = false;

    @Override
    public synchronized Object getResultValue() {
        while (!ready) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result.getResultValue();
    }

    public synchronized void setResult(Result result) {
        this.result = result;
        this.ready = true;
        this.notifyAll();
    }
}
