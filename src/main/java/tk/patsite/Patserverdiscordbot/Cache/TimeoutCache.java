package tk.patsite.Patserverdiscordbot.Cache;

/**
 * The type Timeout cache.
 *
 * @param <T> the type parameter
 */
public class TimeoutCache<T> implements Cache<T>{
    private final long timeoutIfX;
    private final long timeoutIfNotX;
    private long lastTime = -1;
    private T cache;
    private final T x;
    private final GetValueInterface<T> resetCache;

    /**
     * Instantiates a new Timeout cache.
     * The timeout is different if the cache is x and different if it is not.
     *
     * @param timeoutIfX    the timeout if x
     * @param timeoutIfNotX the timeout if not x
     * @param resetCache    the reset cache
     * @param x             the x
     */
    public TimeoutCache(long timeoutIfX, long timeoutIfNotX, GetValueInterface<T> resetCache, T x) {
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
