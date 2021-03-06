package patterns.worker_thread_pattern;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangyang
 * @date 2018/12/25 0025
 */
public class Channel {
    //最大请求数
    private static final int MAX_REQUEST = 100;
    private final LinkedList<Request> requestQueue = new LinkedList<>();
    private final List<WorkerThread> threadPool = new ArrayList<>();

    //初始化线程数
    private final int size;

    public Channel() {
        this(3);
    }

    public Channel(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            threadPool.add(new WorkerThread("Worker-" + i, this));
        }
    }

    public void startWorkers() {
        for (int i = 0; i < size; i++) {
            threadPool.get(i).start();
        }
    }

    public synchronized void putRequest(Request request) throws InterruptedException {
        while (requestQueue.size() >= MAX_REQUEST) {
            this.wait();
        }
        requestQueue.addLast(request);
        this.notifyAll();
    }

    public synchronized Request takeRequest() throws InterruptedException {
        while (requestQueue.size() <= 0) {
            this.wait();
        }
        Request request = requestQueue.removeFirst();
        this.notifyAll();
        return request;
    }

    public void stopAllWorkers() {
        for (int i = 0; i < size; i++) {
            threadPool.get(i).stopThread();
        }
    }
}
