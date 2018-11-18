package ru.job4j.wait;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    List<Integer> buffer = new ArrayList<>();
    private class QueueManipulatingThreads implements Runnable {
        Thread thrd;
        SimpleBlockingQueue<Integer> queue;

        public QueueManipulatingThreads(SimpleBlockingQueue queue, String name) {
            thrd = new Thread(this, name);
            this.queue = queue;
            thrd.start();
        }
        @Override
        public void run() {
            if (thrd.getName().compareTo("Offering") == 0) {
                for (int i = 1; i <= 6; i++) {
                    queue.offer(i);
                }

            } else {
                for (int i = 1; i <= 5; i++) {
                        buffer.add(queue.poll());
                    //System.out.println(queue.poll());
                }
            }
        }
    }

    @Test
    public void whenQueueManipulatedByTwoThreads() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        QueueManipulatingThreads thrd1 = new QueueManipulatingThreads(queue, "Offering");
        QueueManipulatingThreads thrd2 = new QueueManipulatingThreads(queue, "Polling");

        try {
            thrd1.thrd.join();
            thrd2.thrd.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(buffer, is(Arrays.asList(1, 2, 3, 4, 5)));
        //System.out.println("Number of remain elements " + queue.getSize());

    }
}