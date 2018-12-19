package patterns.guarded_suspension_pattern;

import java.util.LinkedList;

/**
 * @author wangyang
 * @date 2018/12/19 0019
 */
public class RequestQuene {
    private final LinkedList<Request> quene = new LinkedList<>();


    public synchronized Request getRequest() {
        while (quene.size() <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return quene.removeFirst();
    }

    public synchronized void putRequest(Request request) {
        quene.addLast(request);
        this.notifyAll();
    }


}
