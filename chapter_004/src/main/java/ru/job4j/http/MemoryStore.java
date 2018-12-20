package ru.job4j.http;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;


public class MemoryStore implements Store {
    private static MemoryStore memoryStoreObject = new MemoryStore();

    private MemoryStore() {
    }

    public static MemoryStore getMemoryStoreObject() {
        return memoryStoreObject;
    }
    private ConcurrentHashMap<String, User> usersList = new ConcurrentHashMap<>();
    @Override
    public boolean add(User user) {
        User u = usersList.putIfAbsent(user.getId(), user);
        return u == null;
    }

    @Override
    public boolean update(String id, User user) {
        boolean result = false;
        User u = findById(id);
        if (u != null) {
            user.setCreateDate(u.getCreateDate());
            result = usersList.replace(id, u, user);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
       return usersList.remove(id, findById(id));
    }

    @Override
    public ArrayList<User> findAll() {

        return new ArrayList<>(usersList.values());
    }

    @Override
    public User findById(String id) {
        return usersList.get(id);
    }
}
