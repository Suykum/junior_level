package ru.job4j.monitor;

import ru.job4j.list.DynamicList;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.Iterator;


@ThreadSafe
public class ThreadSafeDynamicList<T> implements Iterable<T> {
    @GuardedBy("this")
    private DynamicList<T> dynList;

    public ThreadSafeDynamicList() {
        dynList = new DynamicList<>();
    }

    public synchronized void add(T value) {
        dynList.add(value);
    }

    public synchronized T get(int index) {
        return dynList.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.dynList).iterator();
    }

    private DynamicList<T> copy(DynamicList list) {
        DynamicList<T> temp = new DynamicList<>();
        for (Object o : list) {
            temp.add((T) o);
        }
        return temp;
    }
}
