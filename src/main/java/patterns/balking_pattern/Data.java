package patterns.balking_pattern;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author wangyang
 * @date 2018/12/21 0021
 */
public class Data {
    private final String filename;
    private String content;
    private boolean changed;

    public Data(String filename, String content) {
        this.filename = filename;
        this.content = content;
        this.changed = false;
    }

    public synchronized void change(String newContent) {
        this.content = newContent;
        changed = true;
    }

    public synchronized void save() throws IOException {
        if (!changed) {
            return;
        }
        System.out.println(Thread.currentThread().getName() + " save " + content);
        doSave();
        changed = false;
    }

    private void doSave() throws IOException {
        try (Writer writer = new FileWriter(filename, true)) {
            writer.write(content);
            writer.write("\n");
            writer.flush();
        }
    }
}
