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
            if (iterator.next().equals(e)) {
                result = true;
                break;
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
        return set.iterator();
    }
}
