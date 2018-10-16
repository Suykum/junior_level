package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicList<T> implements Iterable<T> {
    private T[] container;
    private int defaultSize = 5;
    private int elemNums;
    private int modCount;

    public DynamicList() {
        container = (T[]) new Object[defaultSize];
        elemNums = 0;
        modCount = 0;
    }

    public void growSize() {
            container = Arrays.copyOf(container, defaultSize += 5);
    }

    public void add(T value) {
        if (elemNums + 1 > defaultSize) {
            growSize();
        }
        container[elemNums++] = value;
        modCount++;
    }
    public T get(int index) {
        return container[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private T[] it = container;
            private int index = 0;
            int expectedModCounter = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCounter) {
                    throw new ConcurrentModificationException();
                }
                return index < elemNums;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return it[index++];
            }
        };
    }
}
