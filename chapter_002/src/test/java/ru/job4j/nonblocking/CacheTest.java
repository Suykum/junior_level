package ru.job4j.nonblocking;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class CacheTest {
    Cache cache = new Cache();
    AtomicReference<Exception> ex = new AtomicReference<>();
    Base base1 = new Base(1, 0);
    Base base2 = new Base(2, 0);
    Base base3 = new Base(3, 0);

    Thread thread1 = new Thread(
            () -> {
                cache.add(base1);
                cache.add(base2);
                cache.add(base3);
                try {
                    for (int i = 0; i < 100000; i++) {
                        cache.update(base1);
                    }
                } catch (OptimisticException e) {
                    ex.set(e);
                }

            }
    );
    Thread thread2 = new Thread(

            () ->  {
                for (int i = 0; i < 100000; i++) {
                    base1.setVersion(i);
                }
            }

    );



   /* @Test
    public void whenTwoThreads() throws InterruptedException {
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println(cache.getVersion(base1));
        Assert.assertThat(ex.get().getMessage(), is("Optimistic Exception found"));


    }*/
}