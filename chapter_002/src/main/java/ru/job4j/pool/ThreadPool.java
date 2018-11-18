package ru.job4j.pool;
import ru.job4j.wait.SimpleBlockingQueue;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
    private int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads;
    private final SimpleBlockingQueue<Runnable> tasks;
    private boolean isStopped;

    public ThreadPool() {
        threads = new ArrayList<>(size);
        tasks = new SimpleBlockingQueue<>();
        isStopped = false;
        for (int i = 0; i < size; i++) {
            try {
                threads.add(new Thread(tasks.poll()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        isStopped = true;
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public void run() {
        while (!isStopped) {
            try {
                Runnable runnable = tasks.poll();
                runnable.run();
            } catch (Exception e) {

            }
        }
    }
}
