package patterns.single_thread_execution_pattern;

/**
 * 相当于线程
 */
public class User extends Thread {
    private final String name;
    private final String address;
    private final Gate gate;

    public User(String name, String address, Gate gate) {
        this.name = name;
        this.address = address;
        this.gate = gate;
    }

    @Override
    public void run() {
        System.out.println(name + "  begin...");
        while (true) {
            this.gate.pass(this.name, this.address);
        }
    }
}
