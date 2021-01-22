package tk.patsite.Patserverdiscordbot.Cache;
/*
MIT License

Copyright (c) 2021 PatrickMSM-Chertes

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

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
