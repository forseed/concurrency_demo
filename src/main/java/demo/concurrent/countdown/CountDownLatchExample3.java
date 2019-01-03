package demo.concurrent.countdown;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample3 {
    private static final Random random = new SecureRandom();

    /**
     * 模拟事件
     */
    static class Event {
        int id;

        Event(int id) {
            this.id = id;
        }

    }

    interface Watcher {
        void done(Table table);
    }

    /**
     * 批量通知的类
     */
    static class TaskBatch implements Watcher {

        private CountDownLatch countDownLatch;
        private TaskGroup taskGroup;

        public TaskBatch(int size, TaskGroup taskGroup) {
            this.countDownLatch = new CountDownLatch(size);
            this.taskGroup = taskGroup;
        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println(table + "finished");
                taskGroup.done(table);
            }
        }
    }

    static class TaskGroup implements Watcher {

        private CountDownLatch countDownLatch;
        private Event event;

        public TaskGroup(int size, Event event) {
            this.countDownLatch = new CountDownLatch(size);
            this.event = event;
        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println("event:" + event.id + " finished");
            }
        }
    }

    /**
     * 模拟数据库表
     */
    static class Table {
        String tableName;
        long sourceRecordCount;
        long targetCount;
        String sourceColumnSchema = "<Table name='sys_user'></Table>";
        String targetColumnSchema;

        public Table(String tableName, long sourceRecordCount) {
            this.tableName = tableName;
            this.sourceRecordCount = sourceRecordCount;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableName='" + tableName + '\'' +
                    ", sourceRecordCount=" + sourceRecordCount +
                    ", targetCount=" + targetCount +
                    ", sourceColumnSchema='" + sourceColumnSchema + '\'' +
                    ", targetColumnSchema='" + targetColumnSchema + '\'' +
                    '}';
        }
    }

    private static List<Table> capture(Event event) {
        List<Table> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Table("table-" + i + ":event-" + event.id, i * 1000));
        }
        return list;
    }

    public static void main(String[] args) {
        Event[] events = {new Event(1), new Event(2)};
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (Event event : events) {
            List<Table> list = capture(event);
            TaskGroup group = new TaskGroup(list.size(), event);
            //每个table校验两个参数
            for (Table table : list) {
                TaskBatch batch = new TaskBatch(2, group);
                TrustSourceCountRecord record = new TrustSourceCountRecord(table, batch);
                TrustSourceColumns columns = new TrustSourceColumns(table, batch);
                service.submit(record);
                service.submit(columns);
            }
        }
    }

    /**
     * 数量校验
     */
    static class TrustSourceCountRecord implements Runnable {
        private final Table table;
        private final TaskBatch batch;

        TrustSourceCountRecord(Table table, TaskBatch batch) {
            this.table = table;
            this.batch = batch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetCount = table.sourceRecordCount;
            batch.done(table);
        }

    }


    /**
     * schema校验
     */
    static class TrustSourceColumns implements Runnable {
        private final Table table;
        private final TaskBatch batch;

        TrustSourceColumns(Table table, TaskBatch batch) {
            this.table = table;
            this.batch = batch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetColumnSchema = table.sourceColumnSchema;
            batch.done(table);
        }

    }
}
