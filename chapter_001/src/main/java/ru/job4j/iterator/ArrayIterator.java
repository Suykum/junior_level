package ru.job4j.iterator;
import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
        return array[outIndex].length > inIndex;
    }

    @Override
    public Integer next() {
        Integer result;
        if (array.length == 0) {
            return -1;
        } else if (inIndex < array[outIndex].length) {

        } else if (outIndex < array.length) {
            ++outIndex;
            inIndex = 0;
        }
        result = array[outIndex][inIndex++];
        return result;
    }
}
