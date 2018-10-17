package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ContainerOnLinkListTest {
    private ContainerOnLinkList<Integer> container;

    @Before
    public void beforeTest() {
        container = new ContainerOnLinkList<>();
        container.addFirst(1);
        container.addFirst(2);
        container.addFirst(3);
    }

    @Test
    public void whenAddNode() {
        assertThat(container.get(1), is(2));
    }
    @Test
    public void whenDeleteNode() {
        assertThat(container.deleteFirst(), is(3));
    }

    @Test
    public void whenHasNextTrue() {
        Iterator<Integer> iterator = container.iterator();
        iterator.next();
        assertThat(iterator.hasNext(), is(true));
    }
    @Test
    public void whenHasNextFalse() {
        Iterator<Integer> iterator = container.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }
    @Test
    public void whenGettingNextElement() {
        Iterator<Integer> iterator = container.iterator();
        iterator.next();
        iterator.next();
        Integer result = iterator.next();
        assertThat(result, is(1));
    }
    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorThrowException() {
        Iterator<Integer> iterator = container.iterator();
        container.addFirst(5);
        iterator.next();
    }

}