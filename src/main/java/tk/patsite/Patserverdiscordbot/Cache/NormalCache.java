package tk.patsite.Patserverdiscordbot.Cache;
/*
MIT License

Copyright (c) 2021 PatrickMSM

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
