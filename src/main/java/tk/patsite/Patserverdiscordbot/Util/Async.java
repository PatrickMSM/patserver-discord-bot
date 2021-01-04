package tk.patsite.Patserverdiscordbot.Util;

public class Async {
    public static Thread NewAsync(Runnable runnable) {
        final Thread th = new Thread(runnable);
        th.start();
        return th;
    }
    public static Thread NewAsyncRepeating(Runnable runnable, long delay) {
        final Thread th = new Thread(()->{
            while (true) {
                runnable.run();
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ignored) {
                    /* ignore */
                }
            }
        });
        th.start();
        return th;
    }

    public static void Stop(Thread th) {
        th.interrupt();
    }
    public static void Restart(Thread th) {
        Stop(th);
        th.start();
    }

}
