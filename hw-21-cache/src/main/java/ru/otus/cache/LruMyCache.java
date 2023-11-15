package ru.otus.cache;

import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class LruMyCache<K, V> {
    private final int capacity;
    private final Map<K, WeakReference<V>> cache;

    public LruMyCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity);
    }

    public boolean exist(K key) {
        WeakReference<V> weakValue = cache.get(key);
        return weakValue != null && weakValue.get() != null;
    }

    public V get(K key){
        WeakReference<V> weakValue = cache.get(key);
        V value = weakValue == null ? null : weakValue.get();

        if(value == null)
            throw new NoSuchElementException();

        cache.put(key, weakValue);
        return value;
    }

    public void add(K key, V value) {
        if (exist(key)) {
                cache.put(key, new WeakReference<>(value));
        }
        else {
            if(cache.size() == capacity)
                cache.remove(cache.keySet().iterator().next());
            cache.put(key, new WeakReference<>(value));
        }
    }

    public void invalidateCache(K key, V value) {
        if (exist(key)) {
            cache.put(key, new WeakReference<>(value));
        }
    }

}
