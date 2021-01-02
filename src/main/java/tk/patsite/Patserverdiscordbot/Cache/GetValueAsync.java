package tk.patsite.Patserverdiscordbot.Cache;

@FunctionalInterface
public interface GetValueAsync<T> {
    T getNewCache();
}
