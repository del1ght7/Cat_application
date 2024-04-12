package com.del1ght7.cat_application.cache;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class InMemoryMap {
    private final Map<String, Object> cache;
    private static final int CAPACITY = 50;
    public InMemoryMap() {
        cache = new LinkedHashMap<>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
                return cache.size() > CAPACITY;
            }
        };
    }
    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public Object get(String key) {
        return cache.get(key);
    }
    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }
    public void remove(String key) {
        cache.remove(key);
    }
    public int size() {
        return cache.size();
    }
}