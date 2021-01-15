package tk.patsite.Patserverdiscordbot.MyLibs;

import tk.patsite.Patserverdiscordbot.Settings;

import java.util.ArrayDeque;
import java.util.Queue;

final class Lock{}

public final class TimedRunnableQueue {
    private final Queue<Runnable> queue = new ArrayDeque<>();
    private final Lock lock = new Lock();
    private boolean isLocked;


    private void tWait(long delay) throws InterruptedException {
        if (isLocked) {
            Thread.sleep(delay);
            System.out.println("locked");
        } else {
            synchronized (lock) {
                lock.wait();
                System.out.println("is");
            }
            System.out.println("os");
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
                    isLocked = true;
                }
            }
        }, "TimedActionQueueThread-" + ID);

        thread.start();
    }

    public void add(Runnable runnable) {
        queue.add(runnable);
        System.out.println(isLocked);
        if (isLocked) {
            System.out.println("lo");
            isLocked = false;
            lock.notify();
        }
    }
}