package demo.example.observer_thread;

import java.util.List;

/**
 * 具体的Observer
 */
public class ThreadLifeCycleObserver implements LifeCycleListener {

    private final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }

        ids.forEach(id -> new Thread(new ObservableRunable(this) {
            @Override
            public void run() {
                try {
                    notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                    System.out.println("query for id " + id);
                    Thread.sleep(1000);
                    int i = 1 / 0;
                    notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                } catch (Exception e) {
                    notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));

                }
            }
        }, id).start());
    }

    @Override
    public void onEvent(ObservableRunable.RunnableEvent event) {
        synchronized (LOCK) {
            System.out.println("the runable[" + Thread.currentThread().getName() + "] state data changed and state is " + event.getState());
            if (event.getCause() != null) {
                System.out.println("the runable[" + Thread.currentThread().getName() + "] process failed");
                event.getCause().printStackTrace();
            }
        }
    }
}
