package demo.concurrent.phaser;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 阶段使用
 */
public class PhaserExample2 {
    private final static Random random = new SecureRandom();

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);
        for (int i = 1; i < 6; i++) {
            new Athletes(i, phaser).start();
        }
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
                System.out.println(no + ": start running");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(no + ": end running");
                phaser.arriveAndAwaitAdvance();
                System.out.println("phase =>" + phaser.getPhase());

                System.out.println(no + ": start bicycle");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(no + ": end bicycle");
                phaser.arriveAndAwaitAdvance();
                System.out.println("phase =>" + phaser.getPhase());

                System.out.println(no + ": start long jump");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(no + ": end long jump");
                phaser.arriveAndAwaitAdvance();
                System.out.println("phase =>" + phaser.getPhase());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
