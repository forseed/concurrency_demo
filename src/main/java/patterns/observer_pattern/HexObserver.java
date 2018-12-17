package patterns.observer_pattern;

public class HexObserver extends Observer {

    public HexObserver(Subject subject) {
        super(subject);
    }

    @Override
    void update() {
        System.out.println("Hex String: "
                + Integer.toHexString(subject.getState()).toUpperCase());

    }
}
