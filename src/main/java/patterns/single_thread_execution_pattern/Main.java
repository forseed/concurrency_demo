package patterns.single_thread_execution_pattern;

public class Main {
    public static void main(String[] args) {
        Gate gate = new Gate();
        User user1 = new User("BeiBei", "Beijing", gate);
        User user2 = new User("Shang", "Shanghai", gate);
        User user3 = new User("Tian", "Tianjing", gate);

        user1.start();
        user2.start();
        user3.start();
    }
}
