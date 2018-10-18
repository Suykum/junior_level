package ru.job4j.list;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class IsCycleTest {
    SimpleArrayList<Integer> list = new SimpleArrayList<>();


    @Test
    public void whenNoCycle() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.hasCycle(), is(false));
    }

    @Test
    public void whenWithCycle() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.setNext(3);
        assertThat(list.hasCycle(), is(true));
    }

}