package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {
    private T[] it;
    private int index;
    public SimpleArrayIterator(T[] o) {
        it = o;
        index = 0;
    }
    @Override
    public boolean hasNext() {
        return index < it.length && it[index] != null;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return it[index++];
    }
}
