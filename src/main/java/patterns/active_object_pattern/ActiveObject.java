package patterns.active_object_pattern;

/**
 * 规定有"主动对象"接口的接口
 */
public interface ActiveObject {
    Result makeString(int count, char fillchar);

    void displayString(String string);
}
