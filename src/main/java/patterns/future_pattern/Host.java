package patterns.future_pattern;

public class Host {
    public Data request(int count, char c) {
        System.out.println("request count:" + count + " c:" + c);
        final FutureData future = new FutureData();
        new Thread(() -> {
            RealData real = new RealData(count, c);
            future.setRealData(real);
        }).start();
        System.out.println("request count:" + count + " c:" + c + " end");
        return future;
    }
}
