package ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleSetTest {
    SimpleSet<Integer> set = new SimpleSet<>();
    @Test
    public void whenSetContainUnique() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(3);
        Iterator<Integer> iteratorOfSet = set.iterator();
        while (iteratorOfSet.hasNext()) {
            System.out.println(iteratorOfSet.next());
        }
    }
}