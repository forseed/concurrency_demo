package patterns.future_pattern;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Host host = new Host();
        Data data1 = host.request(10, 'A');
        Data data2 = host.request(20, 'B');
        Data data3 = host.request(30, 'C');


//        Thread.sleep(5000);

        System.out.println(data1.getContent());
        System.out.println(data2.getContent());
        System.out.println(data3.getContent());
    }
}
