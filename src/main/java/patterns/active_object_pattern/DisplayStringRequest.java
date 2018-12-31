package patterns.active_object_pattern;

/**
 * displayString方法所对应的类，MethodRequestt类的子类
 */
public class DisplayStringRequest extends MethodRequest {
    private final String string;

    public DisplayStringRequest(Servant servant, String string) {
        super(servant, null);
        this.string = string;
    }

    @Override
    public void execute() {
        servant.displayString(string);
    }
}
