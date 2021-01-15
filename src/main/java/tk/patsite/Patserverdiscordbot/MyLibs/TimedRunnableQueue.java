package tk.patsite.Patserverdiscordbot.MyLibs;

import tk.patsite.Patserverdiscordbot.Settings;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

final class Lock{}

public final class TimedRunnableQueue {
    private final Queue<Runnable> queue = new ArrayDeque<>();
    private final Lock lock = new Lock();
    private AtomicBoolean isLocked = new AtomicBoolean(false);


    private void tWait(long delay) throws InterruptedException {
        if (!isLocked.get()) {
            Thread.sleep(delay);
        } else {
            synchronized (lock) {
                lock.wait();
            }
        }
    }

    public TimedRunnableQueue(long delay) {
        // random thread ID
        final long ID = Settings.Misc.RANDOM.nextLong();


        final Thread thread = new Thread(() -> {
            while (true) {
                try {
                    tWait(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                Runnable r = queue.poll();
                if (r != null) {
                    r.run();
                } else {
                    isLocked.set(true);
                }
            }
        }, "TimedActionQueueThread-" + ID);

        thread.start();
    }

    public void add(Runnable runnable) {
        queue.add(runnable);
        if (isLocked.get()) {
            isLocked.set(false);
            lock.notify();
        }
    }
}