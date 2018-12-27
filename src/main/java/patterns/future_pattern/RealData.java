package patterns.future_pattern;

public class RealData implements Data {
    private final String content;

    public RealData(int count, char c) {
        System.out.println(" making realdata count:" + count + " c:" + c);
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++) {
            buffer[i] = c;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(" making realdata count:" + count + " c:" + c + "  end");

        content = String.valueOf(buffer);
    }

    @Override
    public String getContent() {
        return content;
    }
}
