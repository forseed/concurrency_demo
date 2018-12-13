package demo.hook;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

/**
 * 测试shutdownHook
 * 在jvm关闭时候调用(有限制)
 */
public class TestHook {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter fw = new FileWriter("hook.log")) {
                fw.write(LocalDateTime.now() + ">>完成销毁操作,回收内存！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        work();
    }

    private static void work() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(">>>>>>" + i);
        });
    }
}
