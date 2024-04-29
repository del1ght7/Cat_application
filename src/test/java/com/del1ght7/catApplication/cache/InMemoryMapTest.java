package com.del1ght7.catApplication.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryMapTest {

    private InMemoryMap<Integer, String> inMemoryMap;

    @BeforeEach
    void setUp() {
        inMemoryMap = new InMemoryMap<>();
    }

    @Test
    void testPutAndGet() {
        int key = 1;
        String value = "value";

        inMemoryMap.put(key, value);

        assertEquals(value, inMemoryMap.get(key));
    }

    @Test
    void testContainsKey() {
        int key = 1;
        String value = "value";
        inMemoryMap.put(key, value);

        assertTrue(inMemoryMap.containsKey(key));
    }

    @Test
    void testEvict() {
        int key = 1;
        String value = "value";
        inMemoryMap.put(key, value);

        inMemoryMap.evict(key);

        assertFalse(inMemoryMap.containsKey(key));
    }

    @Test
    void testClear() {
        int key1 = 1;
        String value1 = "value1";
        int key2 = 2;
        String value2 = "value2";
        inMemoryMap.put(key1, value1);
        inMemoryMap.put(key2, value2);

        inMemoryMap.clear();

        assertTrue(inMemoryMap.isEmpty());
    }

    @Test
    void testRemoveEldestEntry() {
        int capacity = 3;
        InMemoryMap<Integer, String> customMap = new InMemoryMap<>();
        customMap.put(1, "value1");
        customMap.put(2, "value2");
        customMap.put(3, "value3");

        customMap.put(4, "value4");

        assertTrue(customMap.containsKey(1));
        assertTrue(customMap.containsKey(2));
        assertTrue(customMap.containsKey(3));
        assertTrue(customMap.containsKey(4));
    }
}
