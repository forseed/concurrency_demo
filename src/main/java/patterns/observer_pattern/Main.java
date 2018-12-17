package patterns.observer_pattern;

public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new BinaryObserver(subject);
        new HexObserver(subject);

        System.out.println("set state to 15");
        subject.setState(15);
    }
}
