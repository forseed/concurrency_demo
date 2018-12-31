package patterns.active_object_pattern;

/**
 * 请求建立字符串的线程
 */
public class MakerClientThread extends Thread {
    private final ActiveObject activeObject;
    private final char fillchar;

    public MakerClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
        this.fillchar = name.charAt(0);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                //makeString会立即返回
                //对主动对象送出"建立字符串"的异步消息
                Result result = activeObject.makeString(i, fillchar);
                Thread.sleep(10);
                String value = (String) result.getResultValue();
                System.out.println(Thread.currentThread().getName() + ":value=" + value);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
