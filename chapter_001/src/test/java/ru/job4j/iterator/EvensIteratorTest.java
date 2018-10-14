package ru.job4j.iterator;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class EvensIteratorTest {

    @Test
    public void hasNext() {
        EvensIterator arr = new EvensIterator(new int[] {1, 2, 3, 4, 5, 6});
        arr.next();
        boolean result = arr.hasNext();
        assertThat(result, is(true));
    }

    @Test
    public void next() {
        EvensIterator arr = new EvensIterator(new int[] {1, 2, 3, 4, 5, 6});
        arr.next();
        arr.next();
        int result = arr.next();
        assertThat(result, is(6));
    }
}