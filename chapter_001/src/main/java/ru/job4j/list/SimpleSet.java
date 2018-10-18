package ru.job4j.list;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    private DynamicList<T> set;

    public SimpleSet() {
        set = new DynamicList<>();
    }

    private boolean isContain(T e) {
        Iterator<T> iterator = set.iterator();
        boolean result = false;
        while (iterator.hasNext()) {
            if (iterator.next() == e) {
                result = true;
            }
        }
        return result;
    }
    public void add(T e) {
        if (!isContain(e)) {
            set.add(e);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Iterator<T> iteratorForSet = set.iterator();
            @Override
            public boolean hasNext() {
                return iteratorForSet.hasNext();
            }

            @Override
            public T next() {
                return iteratorForSet.next();
            }
        };
    }
}
