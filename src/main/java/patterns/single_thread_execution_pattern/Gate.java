package patterns.single_thread_execution_pattern;

/**
 * 相当于共享资源
 */
public class Gate {
    private int count = 0;
    private String name = "Nobody";
    private String address = "Nowhere";

    /**
     * 相当于临界值
     */
    public void pass(String name, String address) {
        this.count++;
        /**
         * 存在竞争
         */
        this.name = name;
        this.address = address;
        verify();
    }

    private void verify() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println("-------------BROKEN-------" + toString());
        }
    }

    @Override
    public String toString() {
        return "No." + count + ":" + name + "," + address;
    }
}
