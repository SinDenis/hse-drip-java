package server.store;

import java.util.Set;

public interface KeyValueStore {
    void put(String key, String value);
    String get(String key);
    String delete(String key);
    Set<String> keys();
    int size();
}
