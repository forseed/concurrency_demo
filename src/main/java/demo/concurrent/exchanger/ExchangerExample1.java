package demo.concurrent.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * 模拟交换person信息
 */
public class ExchangerExample1 {
    public static void main(String[] args) {
        Exchanger<Person> exchanger = new Exchanger<>();

        new Thread(() -> {
            try {
                System.out.println("alice is preparing");
                Person person = exchanger.exchange(new Person("alice", 23));
                System.out.println("alice recive message from " + person.name + "  " + person);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println("demo is preparing");
                TimeUnit.SECONDS.sleep(3);
                Person person = exchanger.exchange(new Person("demo", 25));
                System.out.println("demo recive message from " + person.name + "  " + person);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static class Person {
        final String name;
        final int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
