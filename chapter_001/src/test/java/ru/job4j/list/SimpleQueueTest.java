package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {
    SimpleQueue<Integer> queue = new SimpleQueue<>();
    @Test
    public void whenPushOneThenPollingOne() {
        queue.push(1);
        queue.push(2);
        queue.push(3);
        Integer result = queue.poll();
        assertThat(result, is(1));
    }

    @Test
    public void whenPushAndPollCombined() {
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        queue.push(9);
        queue.push(10);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

    }
}