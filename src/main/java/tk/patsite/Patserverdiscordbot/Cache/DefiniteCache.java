package tk.patsite.Patserverdiscordbot.Cache;

public class DefiniteCache<T> implements Cache<T>{
    private T cache;

    public T get(GetValueInterface<T> getCache)  {
        if (cache == null) {
            cache = getCache.get();
        }
        return cache;
    }

    @Override
    public T get() {
        return null;
    }
}
