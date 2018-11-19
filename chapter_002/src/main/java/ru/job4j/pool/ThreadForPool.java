package ru.job4j.pool;

import org.apache.log4j.Logger;
import ru.job4j.wait.SimpleBlockingQueue;

public class ThreadForPool implements Runnable {
    private final SimpleBlockingQueue<Runnable> tasks;
    private static final Logger LOGGER = Logger.getLogger(ThreadPool.class);

    public ThreadForPool(final SimpleBlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Runnable runnable = tasks.poll();
                LOGGER.info(Thread.currentThread().getName() + " Get task from queue");
                if (runnable != null) {
                    runnable.run();
                    LOGGER.info(Thread.currentThread().getName() + " start task");
                }
            } catch (Exception e) {
                LOGGER.info(e.getMessage(), e);
                Thread.currentThread().interrupt();
            }
        }


    }
}
