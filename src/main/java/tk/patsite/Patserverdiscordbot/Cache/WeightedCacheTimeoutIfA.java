package tk.patsite.Patserverdiscordbot.Cache;

public class WeightedCacheTimeoutIfA<T> extends Cache<T>{
    private final long timeoutIfX;
    private final long timeoutIfNotX;
    private long lastTime = -1;
    private T cache;
    private final T x;
    private final GetValueAsync<T> resetCache;

    public WeightedCacheTimeoutIfA(long timeoutIfX, long timeoutIfNotX, GetValueAsync<T> resetCache, T x) {
        this.timeoutIfX = timeoutIfX;
        this.timeoutIfNotX = timeoutIfNotX;
        this.resetCache = resetCache;
        this.x = x;
    }

    @Override
    public T get()  {
        if (cache == x) {
            if (System.currentTimeMillis() - lastTime >= timeoutIfX) {
                cache = resetCache.getNewCache();
                lastTime = System.currentTimeMillis();

            }
            System.out.println("x");
        } else {
            if (System.currentTimeMillis() - lastTime >= timeoutIfNotX) {
                cache = resetCache.getNewCache();
                lastTime = System.currentTimeMillis();

            }
            System.out.println("not x");
        }

        return cache;
    }
}
