package ru.job4j.monitor;

import org.junit.Test;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ThreadSafeDynamicListTest {

    private class Thread1 extends Thread {
        ThreadSafeDynamicList<String> list;

        public Thread1(ThreadSafeDynamicList<String> list) {
            this.list = list;
        }

        public void run() {
            list.add("one");
            list.add("two");
            list.add("three");
            System.out.println(Thread.currentThread().getName() + " " + list.get(1));
        }
    }

    private class Thread2 extends Thread {
        ThreadSafeDynamicList<String> list;

        public Thread2(ThreadSafeDynamicList<String> list) {
            this.list = list;
        }

        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Iterator<String> iterator = list.iterator();
            System.out.println(Thread.currentThread().getName() + " " + iterator.next());
            System.out.println(Thread.currentThread().getName() + " " + iterator.next());
            System.out.println(Thread.currentThread().getName() + " " + iterator.next());

        }
    }

    @Test
    public void whenDynListManipulatedByTwoThreads() {
        ThreadSafeDynamicList<String> list = new ThreadSafeDynamicList<>();
        Thread first = new Thread1(list);
        Thread second = new Thread2(list);
        first.start();
        second.start();
        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(list.get(2), is("three"));
    }

}