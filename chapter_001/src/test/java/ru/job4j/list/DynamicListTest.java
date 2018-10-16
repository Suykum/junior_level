package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DynamicListTest {
    DynamicList<Integer> dynList;
    @Before
    public void beforeTest() {
        dynList = new DynamicList<>();
        dynList.add(1);
        dynList.add(2);
        dynList.add(3);
        dynList.add(4);
    }

    @Test
    public void whenAddOnDefaultSizeArray() {
        dynList.add(5);
        assertThat(dynList.get(4), is(5));
    }
    @Test
    public void whenAddOnChangedSizeArray() {
        dynList.add(5);
        dynList.add(6);
        dynList.add(7);
        assertThat(dynList.get(6), is(7));
    }
    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorThrowException() {
        Iterator<Integer> iterator = dynList.iterator();
        dynList.add(5);
        System.out.println(iterator.next());
    }
}