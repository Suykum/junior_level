package ru.job4j.pool;

public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool();
        threadPool.work(() -> System.out.println("First Task"));
        threadPool.work(() -> System.out.println("Second Task"));
        threadPool.work(() -> System.out.println("Third Task"));

        threadPool.shutdown();

    }

}