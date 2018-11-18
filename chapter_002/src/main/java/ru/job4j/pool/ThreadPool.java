package ru.job4j.pool;
import ru.job4j.wait.SimpleBlockingQueue;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads;
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        threads = new ArrayList<>(size);
        tasks = new SimpleBlockingQueue<>();
        StartWork(size);
    }

    private void StartWork(int size) {
        for (int i = 0; i < size; i++) {
            Thread e = new Thread(new ThreadForPool(tasks));
            threads.add(e);
            e.start();
        }
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
