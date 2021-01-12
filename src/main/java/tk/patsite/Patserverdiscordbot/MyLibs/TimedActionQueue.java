package tk.patsite.Patserverdiscordbot.MyLibs;

import tk.patsite.Patserverdiscordbot.Settings;

import java.util.ArrayDeque;
import java.util.Queue;

public final class TimedActionQueue {
    private final Queue<Runnable> queue = new ArrayDeque<>();
    private final long ID = Settings.Misc.RANDOM.nextLong(); // random thread ID
    private final Thread thread;

    private void stop() {
        thread.interrupt();
    }

    public void add(Runnable runnable) {
        queue.add(runnable);
        thread.start();
    }

    public TimedActionQueue() {
        Action action = () -> {
            while (true) {
                try {Thread.sleep(1000);
                } catch (InterruptedException ignored) {/* ignored */}
                Runnable r = queue.poll();
                if (r == null) {
                    stop();
                } else {
                    r.run();
                }
            }
        };


        thread = new Thread(action::run, "TimedActionQueueThread-"+ID);
        stop();
    }

}


@FunctionalInterface
interface Action {
    void run();
}