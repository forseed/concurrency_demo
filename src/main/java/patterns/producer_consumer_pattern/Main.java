package patterns.producer_consumer_pattern;

/**
 * @author wangyang
 * @date 2018/12/21 0021
 */
public class Main {
    public static void main(String[] args) {
        Table table = new Table(3);
        new MakerThread("Maker1",table).start();
        new MakerThread("Maker2",table).start();
        new MakerThread("Maker3",table).start();


        new EaterThread("Eater1",table).start();
        new EaterThread("Eater2",table).start();
        new EaterThread("Eater3",table).start();



    }
}
