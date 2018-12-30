package patterns.future_pattern;

import java.util.function.Consumer;

public class Host {
    public Data request(int count, char c, Consumer<String> consumer) {
        System.out.println("request count:" + count + " c:" + c);
        final FutureData future = new FutureData();
        new Thread(() -> {
            RealData real = new RealData(count, c);
            future.setRealData(real);
            consumer.accept(real.getContent());
        }).start();
        System.out.println("request count:" + count + " c:" + c + " end");
        return future;
    }
}
