package tk.patsite.Patserverdiscordbot.Cache;

public class WeightedCacheTimeoutIfX<T> extends Cache<T>{
    private final long timeoutIfX;
    private final long timeoutIfNotX;
    private long lastTime = -1;
    private T cache;
    private final T x;
    private final GetValueInterface<T> resetCache;

    public WeightedCacheTimeoutIfX(long timeoutIfX, long timeoutIfNotX, GetValueInterface<T> resetCache, T x) {
        this.timeoutIfX = timeoutIfX;
        this.timeoutIfNotX = timeoutIfNotX;
        this.resetCache = resetCache;
        this.x = x;
    }

    @Override
    public T get()  {
        if (cache == x) {
            if (System.currentTimeMillis() - lastTime >= timeoutIfX) {
                cache = resetCache.get();
                lastTime = System.currentTimeMillis();
            }
        } else {
            if (System.currentTimeMillis() - lastTime >= timeoutIfNotX) {
                cache = resetCache.get();
                lastTime = System.currentTimeMillis();
            }
        }

        return cache;
    }
}
