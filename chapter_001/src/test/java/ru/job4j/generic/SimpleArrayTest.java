package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

    SimpleArray<Integer> iArr = new SimpleArray<>(5);
    SimpleArray<String> strArr = new SimpleArray<>(4);

    @Test
    public void whenAddInteger() {
        iArr.add(1);
        iArr.add(2);
        Integer result = iArr.get(1);
        assertThat(result, is(2));
    }

    @Test
    public void whenSetInteger() {
        iArr.add(1);
        iArr.add(2);
        iArr.set(1, 10);
        Integer result = iArr.get(1);
        assertThat(result, is(10));
    }

    @Test
    public void whenDeleteInteger() {
        iArr.add(1);
        iArr.add(2);
        iArr.add(8);
        iArr.delete(1);
        Integer result = iArr.get(1);
        assertThat(result, is(8));
    }

    @Test
    public void whenStringAdd() {
        strArr.add("five");
        strArr.add("one");
        String result = strArr.get(1);
        assertThat(result, is("one"));
    }
    @Test
    public void whenStringSet() {
        strArr.add("one");
        strArr.add("two");
        strArr.set(1, "ten");

        String result = strArr.get(1);
        assertThat(result, is("ten"));
    }

    @Test
    public void whenStringDelete() {
        strArr.add("one");
        strArr.add("two");
        strArr.delete(0);
        String result = strArr.get(0);
        assertThat(result, is("two"));
    }
    @Test
    public void whenIteratorHasNextTested() {
        strArr.add("one");
        strArr.add("two");
        strArr.add("three");
        Iterator<String> strIt = strArr.iterator();
        strIt.next();
        strIt.next();
        strIt.next();
        boolean result = strIt.hasNext();
        assertThat(result, is(false));
    }

    @Test
    public void whenIteratorNextTested() {
        Iterator<String> strIt = strArr.iterator();
        strArr.add("one");
        strArr.add("two");
        strArr.add("three");
        strIt.next();
        String result = strIt.next();
        assertThat(result, is("two"));
    }
}