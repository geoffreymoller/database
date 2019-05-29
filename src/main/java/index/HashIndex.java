package index;

import java.util.HashMap;

public class HashIndex<K, V> {

    private HashMap<K, V> map;

    HashIndex() {
        this.map = new HashMap<>();
    }

    public void put(K title, V index) {
        this.map.put(title, index);
    }

    public V get(K title) {
        return map.get(title);
    }
}
