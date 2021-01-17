package tk.patsite.Patserverdiscordbot.MyLibs;

import tk.patsite.Patserverdiscordbot.Settings;

import java.util.ArrayDeque;
import java.util.Queue;

public final class TimedRunnableQueue {
    private final Queue<Runnable> queue = new ArrayDeque<>();

    public TimedRunnableQueue(long delay) {
        // random thread ID
        final long ID = Settings.NonSettings.RANDOM.nextLong();


        final Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                Runnable r = queue.poll();
                if (r != null) {
                    r.run();
                }
            }
        }, "TimedActionQueueThread-" + ID);

        thread.start();
    }

    public void add(Runnable runnable) {
        queue.add(runnable);
    }
}