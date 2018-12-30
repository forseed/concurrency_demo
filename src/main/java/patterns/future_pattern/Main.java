package patterns.future_pattern;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Host host = new Host();
        Data data1 = host.request(10, 'A', System.out::println);
        Data data2 = host.request(20, 'B', System.out::println);
        Data data3 = host.request(30, 'C', System.out::println);


//        Thread.sleep(5000);

//        System.out.println(data1.getContent());
//        System.out.println(data2.getContent());
//        System.out.println(data3.getContent());
    }
}
