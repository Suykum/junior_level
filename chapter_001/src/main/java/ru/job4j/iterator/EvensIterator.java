package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvensIterator implements Iterator {
    private int[] array;
    private int index;

    public EvensIterator(int[] givenArray) {
        array = givenArray;
        index = 0;
    }

    private int evenIndex() {
        for (; index < array.length; index++) {
            if (array[index] % 2 == 0) {
            break;
            }
        }
        return index;
    }
    @Override
    public boolean hasNext() {
        return array.length > evenIndex();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        //index = evenIndex();
        return array[index++];
    }
}
