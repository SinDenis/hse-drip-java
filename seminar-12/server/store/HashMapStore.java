package server.store;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapStore implements KeyValueStore {
    private final Map<String, String> map = new HashMap<>();

    @Override
    public void put(String key, String value) {
        map.put(key, value);
    }

    @Override
    public String get(String key) {
        return map.get(key);
    }

    @Override
    public String delete(String key) {
        return map.remove(key);
    }

    @Override
    public Set<String> keys() {
        return map.keySet();
    }

    @Override
    public int size() {
        return map.size();
    }
}
