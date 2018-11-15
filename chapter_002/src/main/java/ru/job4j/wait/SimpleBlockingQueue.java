package ru.job4j.wait;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    int queueCapacity;
    @GuardedBy("this")
    private Queue<T> queue;
    private boolean full = false;
    public SimpleBlockingQueue() {
        queue = new LinkedList<>();
        queueCapacity = 5;
    }

    public synchronized void offer(T value) {
        if (queue.size() == queueCapacity) {
            full = true;
            notify();
        }

        while (full  && Thread.currentThread().isInterrupted()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.offer(value);
    }

    public synchronized T poll() {
        if (queue.isEmpty()) {
            full = false;
            notify();
        }

        while (!full  && !Thread.currentThread().isInterrupted()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.poll();
    }

    public synchronized int getSize() {
        return queue.size();
    }
}
