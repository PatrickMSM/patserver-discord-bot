package tk.patsite.Patserverdiscordbot.Cache;

public class NormalCache<T> extends Cache<T>{
    private final long timeout;
    private long lastTime = -1;
    private T cache;
    private final GetValueAsync<T> resetCache;

    public NormalCache(long timeout, GetValueAsync<T> resetCache) {
        this.timeout = timeout;
        this.resetCache = resetCache;
    }

    @Override
    public T get()  {
        if (System.currentTimeMillis() - lastTime >= timeout) {
            cache = resetCache.getNewCache();
            lastTime = System.currentTimeMillis();
        }
        return cache;
    }
}
