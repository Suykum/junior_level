package ru.job4j.monitor;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserStorageTest {
    User user1 = new User(1, 100);
    User user2 = new User(2, 200);
    User user3 = new User(3, 300);
    User user4 = new User(4, 400);


    private class UserStorageThread1 extends Thread {
        private final UserStorage userStorage;

        private UserStorageThread1(final UserStorage userStorage) {
            this.userStorage = userStorage;
        }

        @Override
        public void run() {
            this.userStorage.add(user1);
            this.userStorage.add(user2);
            this.userStorage.add(user3);
        }
    }
    private class UserStorageThread2 extends Thread {
        private final UserStorage userStorage;

        private UserStorageThread2(final UserStorage userStorage) {
            this.userStorage = userStorage;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.userStorage.delete(user1);
            this.userStorage.transfer(2, 3, 50);
            this.userStorage.add(user4);
        }
    }

    @Test
    public void whenUserStorageModifiedByTwoThreads() {
        UserStorage userStorage = new UserStorage();
        Thread thread1 = new UserStorageThread1(userStorage);
        Thread thread2 = new UserStorageThread2(userStorage);
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(userStorage.getUser(0).getId(), is(2));
        assertThat(userStorage.getUser(0).getAmount(), is(150));
        assertThat(userStorage.getUser(2).getId(), is(4));

    }
}