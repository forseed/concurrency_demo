package patterns.active_object_pattern;

public class Main {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFacory.createActiveObject();
        new MakerClientThread("Alice", activeObject).start();
        new MakerClientThread("Demo", activeObject).start();
        new DisplayClientThread("Anna", activeObject).start();
    }
}
