package ru.job4j.iterator;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
public class ArrayIteratorTest {

    @Test
    public void whenHasNextFalse() {
        int[][] givenArray = {{1, 2}, {4, 5}};
        ArrayIterator arr = new ArrayIterator(givenArray);
        arr.next();
        arr.next();
        arr.next();
        System.out.println(arr.next());
        boolean result = arr.hasNext();
        assertThat(result, is(false));
    }

    @Test
    public void whenGetThirdElementFromArray() {
        int[][] givenArray = {{1, 2}, {4, 5, 6}};
        ArrayIterator arr = new ArrayIterator(givenArray);
        arr.next();
        arr.next();
        int result = arr.next();
        assertThat(result, is(4));

    }

    @Test
    public void whenGetFourthElementFromArray() {
        int[][] givenArray = {{1}, {2, 3}, {6, 7}, {8, 9, 10}};
        ArrayIterator arr = new ArrayIterator(givenArray);
        arr.next();
        arr.next();
        arr.next();
        int result = arr.next();
        assertThat(result, is(6));

    }
}