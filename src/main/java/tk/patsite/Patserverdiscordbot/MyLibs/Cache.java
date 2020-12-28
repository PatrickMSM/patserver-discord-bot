package tk.patsite.Patserverdiscordbot.MyLibs;

public class Cache<T> {
    private final long timeout;
    private long lastTime = -1;
    private T cache;
    private final CacheResetCallback<T> resetCache;

    public Cache(long timeout, CacheResetCallback<T> resetCache) {
        this.timeout = timeout;
        this.resetCache = resetCache;

    }

    public T get() {
        if (System.currentTimeMillis() - lastTime >= timeout) {
            cache = resetCache.getNewCache();
            lastTime = System.currentTimeMillis();
        }
        return cache;
    }
}

