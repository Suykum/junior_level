package ru.job4j.iterator;
import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayIterator implements Iterator {
    private int[][] array;
    private int inIndex;
    private int outIndex;

    public ArrayIterator(int[][] givenArr) {
        array = givenArr;
        inIndex = 0;
        outIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return !(outIndex >= array.length - 1 && inIndex >= array[outIndex].length);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else if (!(inIndex < array[outIndex].length)) {
            ++outIndex;
            inIndex = 0;
        }
        return array[outIndex][inIndex++];
    }
}
