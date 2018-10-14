package ru.job4j.iterator;
import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayIterator implements Iterator {
    private int[][] array;
    private int index;
    List<Integer> listOfArray;

    public ArrayIterator(int[][] givenArr) {
        array = givenArr;
        index = 0;
        listOfArray = Stream.of(array).flatMapToInt(x -> Arrays.stream(x)).boxed().collect(Collectors.toList());
    }

    @Override
    public boolean hasNext() {
        return listOfArray.size() > index;
    }

    @Override
    public Integer next() {
        return listOfArray.get(index++);
    }
}
