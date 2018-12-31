package patterns.active_object_pattern;


/**
 * 进行实际处理的类
 */
public class Servant implements ActiveObject {
    @Override
    public Result makeString(int count, char fillchar) {
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++) {
            buffer[i] = fillchar;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new RealResult(String.valueOf(buffer));
    }

    @Override
    public void displayString(String string) {
        try {
            System.out.println("displayString:" + string);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
