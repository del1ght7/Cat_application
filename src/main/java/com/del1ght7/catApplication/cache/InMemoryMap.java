package com.del1ght7.catApplication.cache;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class InMemoryMap<K, V> {
    private static final int CAPACITY = 50;
    private final Map<K, V> cache  = new LinkedHashMap<>(CAPACITY, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > CAPACITY;
        }
    };

    public V get(K key) {
        return cache.get(key);
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    public void evict(K key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }
    public boolean isEmpty() {
        return cache.isEmpty();
    }
}