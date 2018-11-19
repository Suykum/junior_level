package ru.job4j.pool;
import org.apache.log4j.Logger;
import ru.job4j.wait.SimpleBlockingQueue;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
    private static final Logger LOGGER = Logger.getLogger(ThreadPool.class);
    private final List<Thread> threads;
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        threads = new ArrayList<>(size);
        tasks = new SimpleBlockingQueue<>();
        startWork(size);
    }

    private void startWork(int size) {
        LOGGER.info(size + " threads will be created in the pool");
        for (int i = 0; i < size; i++) {
            Thread e = new Thread(new ThreadForPool(tasks));
            threads.add(e);
            e.start();
        }
    }

    public void work(Runnable job) {
        tasks.offer(job);
        LOGGER.info("The task added to the pool");
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
        LOGGER.info("All threads closed");
    }
}
