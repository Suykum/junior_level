package ru.job4j.generic;

import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {
    private T[] ob;
    private int index;
    public SimpleArray(int size) {
        ob = (T[]) new Object[size];
        index = 0;
    }

    public void add(T model) {
        if (index < ob.length) {
            ob[index++] = model;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void set(int i, T model) {
        ob[i] = model;
    }

    public void delete(int i) {
        int numElts = ob.length - (i + 1);
        System.arraycopy(ob, i + 1, ob, i, numElts);
        index--;
    }

    public T get(int i) {
        return ob[i];
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<>(ob);
    }
}
