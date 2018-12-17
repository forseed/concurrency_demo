package demo.example.observer_thread;

/**
 * Obsever角色
 */
public interface LifeCycleListener {

    void onEvent(ObservableRunable.RunnableEvent event);
}
