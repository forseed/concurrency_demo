package patterns.future_pattern;

public class FutureData implements Data {
    private RealData realData;
    private boolean ready = false;

    @Override
    public synchronized String getContent() {
        while (!ready) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getContent();
    }

    public synchronized void setRealData(RealData realData) {
        if (ready) {
            return;
        }
        this.realData = realData;
        this.ready = true;
        notifyAll();
    }
}
