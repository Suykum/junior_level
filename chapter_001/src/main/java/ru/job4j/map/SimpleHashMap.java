package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable <SimpleEntry>{
    private int defaultSize;
    private SimpleEntry<K, V>[] table;
    private int numElem;
    private double loadFactor;

    public SimpleHashMap() {
        defaultSize = 16;
        table = new SimpleEntry[defaultSize];
        loadFactor = 0.75;
    }

    public boolean insert(K key, V value) {
        if ((double) numElem / (double) table.length >= loadFactor) {
            tableResize();
        }
        boolean result = false;
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new SimpleEntry<>(key, value);
            result = true;
            numElem++;
        }


        return result;
    }

    public boolean delete(K key) {
        boolean result = false;
        int index = hash(key);
        if (table[index] != null) {
            table[index] = null;
            result = true;
            numElem--;
        }
        return result;
    }

    public V get(K key) {
        int index = hash(key);
        return table[index].getValue();
    }

    public void tableResize() {
        numElem = 0;
        SimpleEntry<K, V>[] temp = table;
        table = new SimpleEntry[table.length * 2];
        for (SimpleEntry entry : temp) {
            if (entry != null) {
                insert((K) entry.getKey(), (V) entry.getValue());
            }
        }
    }

    public int hash(K key){
    return (key.hashCode() & 0x7fffffff) % table.length;
    }

    public void displayMap() {
        for (SimpleEntry e : table) {
            if (e != null) {
                System.out.println("Key: " + e.getKey() + ", Value: " + e.getValue());
            }
        }
    }

    public int tableSize() {
        return table.length;
    }

    public Integer getIndex(K key) {
        Integer index = null;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i].getKey() == key) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
           private int index = 0;
           SimpleEntry<K, V>[] iterator = table;

            @Override
            public boolean hasNext() {
                return index < table.length;
            }

            @Override
            public SimpleEntry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (hasNext()) {
                    if (iterator[index] != null) {
                        break;
                    }
                    index++;
                }
                return iterator[index++];
            }
        };
    }
}
