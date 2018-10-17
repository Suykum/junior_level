package ru.job4j.list;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {
    SimpleStack<Integer> stack = new SimpleStack<>();;

    @Test
    public void poll() {
        stack.push(1);
        stack.push(2);
        assertThat(stack.poll(), is(2));
    }
}