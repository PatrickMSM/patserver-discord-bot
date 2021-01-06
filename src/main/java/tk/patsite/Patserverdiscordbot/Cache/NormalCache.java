package tk.patsite.Patserverdiscordbot.Cache;

public class NormalCache<T> implements Cache<T>{
    private final long timeout;
    private long lastTime = -1;
    private T cache;
    private final GetValueInterface<T> resetCache;

    public NormalCache(long timeout, GetValueInterface<T> resetCache) {
        this.timeout = timeout;
        this.resetCache = resetCache;
    }

    @Override
    public T get()  {
        if (System.currentTimeMillis() - lastTime >= timeout) {
            cache = resetCache.get();
            lastTime = System.currentTimeMillis();
        }
        return cache;
    }
}
