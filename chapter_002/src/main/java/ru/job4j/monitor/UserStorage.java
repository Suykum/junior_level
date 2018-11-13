package ru.job4j.monitor;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private List<User> storage = new ArrayList<>();

    public synchronized void add(User user) {
        if (!storage.contains(user)) {
            storage.add(user);
        }
    }

    public synchronized boolean delete(User user) {
        return storage.remove(user);
    }

    public synchronized void transfer(int fromId, int toId, int amount) {

        for (User u : storage) {
            if (u.getId() == fromId) {
                u.setAmount(u.getAmount() - amount);
            }
        }
        for (User u : storage) {
            if (u.getId() == toId) {
                u.setAmount(u.getAmount() + amount);
            }
        }
    }

    /**
     * Only for test purpose
     */
    public User getUser(int index) {
        return storage.get(index);
    }
}
