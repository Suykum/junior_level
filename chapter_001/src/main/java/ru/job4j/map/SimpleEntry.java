package ru.job4j.map;

public class SimpleEntry<K, V> {
    private K key;
    private V value;

    public SimpleEntry(K key, V value) {
        this.setKey(key);
        this.setValue(value);
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
