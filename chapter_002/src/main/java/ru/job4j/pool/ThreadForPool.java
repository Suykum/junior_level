package ru.job4j.pool;

import ru.job4j.wait.SimpleBlockingQueue;

public class ThreadForPool implements Runnable {
    Thread thrd;
    SimpleBlockingQueue<Runnable> tasks;

    public ThreadForPool(SimpleBlockingQueue<Runnable> tasks) {
        thrd = new Thread(this);
        this.tasks = tasks;
    }
    @Override
    public void run() {
        while (tasks.poll() != null) {
            try {
                Runnable runnable = tasks.poll();
                runnable.run();
            } catch (Exception e) {

            }
        }


    }
}
