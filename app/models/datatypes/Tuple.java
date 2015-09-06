package models.datatypes;

public class Tuple<K, V> {

    private final K key;
    private final V val;

    public Tuple(K key, V val) {

        this.key = key;
        this.val = val;
    }

    public K getKey() {
        return key;
    }

    public V getVal() {
        return val;
    }

}
