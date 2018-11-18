package ru.job4j.pool;

import ru.job4j.wait.SimpleBlockingQueue;

public class ThreadForPool implements Runnable {
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadForPool(final SimpleBlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Runnable runnable = tasks.poll();
                if (runnable != null) {
                    runnable.run();
                }
            } catch (Exception e) {

            }
        }


    }
}
