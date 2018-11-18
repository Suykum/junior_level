package ru.job4j.pool;
import org.apache.log4j.Logger;


public class ThreadPoolTest {
    static Logger logger = Logger.getLogger(ThreadPool.class);

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool();
        threadPool.work(() -> System.out.println("First Thread"));
        threadPool.work(() -> System.out.println("Second Thread"));

        threadPool.shutdown();

    }

}