package demo.concurrent.phaser;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 阶段使用并且可以随时减少part
 */
public class PhaserExample3 {
    private final static Random random = new SecureRandom();

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(3) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("phase" + phase);
                return super.onAdvance(phase, registeredParties);
            }
        };
        for (int i = 1; i < 3; i++) {
            new Athletes(i, phaser).start();
        }
        new Athletes.InjuredAthletes(3, phaser).start();
    }

    static class Athletes extends Thread {
        private final Phaser phaser;
        private final int no;

        Athletes(int no, Phaser phaser) {
            this.phaser = phaser;
            this.no = no;
        }

        @Override
        public void run() {
            try {
                sport(no, phaser, ": start running", ": end running");

                sport(no, phaser, ": start bicycle", ": end bicycle");

                sport(no, phaser, ": start long jump", ": end long jump");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        static class InjuredAthletes extends Thread {
            private final Phaser phaser;
            private final int no;

            InjuredAthletes(int no, Phaser phaser) {
                this.phaser = phaser;
                this.no = no;
            }

            @Override
            public void run() {
                try {
                    sport(no, phaser, ": start running", ": end running");

                    sport(no, phaser, ": start bicycle", ": end bicycle");

                    System.out.println(no + ": i get injured,have to exit");
                    phaser.arriveAndDeregister();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private static void sport(int no, Phaser phaser, String s, String s2) throws InterruptedException {
            System.out.println(no + s);
            TimeUnit.SECONDS.sleep(random.nextInt(5));
            System.out.println(no + s2);
            phaser.arriveAndAwaitAdvance();
        }
    }
}
