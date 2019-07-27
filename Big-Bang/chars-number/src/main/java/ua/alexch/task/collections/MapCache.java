package ua.alexch.task.collections;

import java.util.HashMap;
import java.util.Map;

public class MapCache implements Cache {
    private final Map<String, String> cache;

    MapCache() {
        this.cache = new HashMap<>();
    }

    @Override
    public boolean isContain(String key) {
        return cache.containsKey(key) ? true : false;
    }

    @Override
    public String getValue(String key) {
        return cache.get(key);
    }

    @Override
    public void put(String key, String value) {
        cache.put(key, value);
    }
}
