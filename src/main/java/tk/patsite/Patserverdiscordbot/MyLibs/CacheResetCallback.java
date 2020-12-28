package tk.patsite.Patserverdiscordbot.MyLibs;

@FunctionalInterface
public interface CacheResetCallback<T> {
    T getNewCache();
}
