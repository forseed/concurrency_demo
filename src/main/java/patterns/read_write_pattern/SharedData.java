package patterns.read_write_pattern;

import java.util.Arrays;

/**
 * 共享资源
 */
public class SharedData {
    private final char[] buffer;
    private final ReadWriteLock lock = new ReadWriteLock();

    /**
     * 初始化时已*号填充
     *
     * @param size size
     */
    public SharedData(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < size; i++) {
            buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        try {
            lock.readLock();
            char[] newBuf = Arrays.copyOf(buffer, buffer.length);
            sleep(50);
            return newBuf;
        } finally {
            lock.readUnLock();
        }
    }

    public void write(char c) throws InterruptedException {
        try {
            lock.writeLock();
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = c;
                sleep(10);
            }

        } finally {
            lock.writeUnLock();
        }
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
