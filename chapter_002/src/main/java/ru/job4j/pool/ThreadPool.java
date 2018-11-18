package ru.job4j.pool;
import ru.job4j.wait.SimpleBlockingQueue;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
    private int size = Runtime.getRuntime().availableProcessors();
    private final List<ThreadForPool> threads;
    private final SimpleBlockingQueue<Runnable> tasks;


    public ThreadPool() {
        threads = new ArrayList<>(size);
        tasks = new SimpleBlockingQueue<>();
        for (int i = 0; i < size; i++) {
            threads.add(new ThreadForPool(tasks));
        }
        for (ThreadForPool thread : threads) {
            thread.thrd.start();
        }
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        for (ThreadForPool thread : threads) {
            thread.thrd.interrupt();
        }
    }
}
