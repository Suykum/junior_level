package ru.job4j.wait;

import org.junit.Test;

public class SimpleBlockingQueueTest {
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
                for (int i = 1; i <= 4; i++) {
                    System.out.println(queue.poll());
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

        System.out.println("Number of remain elements " + queue.getSize());


    }
}