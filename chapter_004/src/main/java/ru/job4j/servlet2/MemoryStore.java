package ru.job4j.servlet2;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class MemoryStore implements Store {
    private static MemoryStore memoryStoreObject = new MemoryStore();

    private MemoryStore() {
    }

    public static MemoryStore getMemoryStoreObject() {
        return memoryStoreObject;
    }
    private ConcurrentHashMap<UUID, User> usersList = new ConcurrentHashMap<>();
    @Override
    public boolean add(User user) {
        UUID id = UUID.randomUUID();
        user.setId(id);
        User u = usersList.putIfAbsent(id, user);
        return u == null;
    }

    @Override
    public boolean update(UUID id, User user) {
        boolean result = false;
        User u = findById(id);
        if (u != null) {
            user.setCreateDate(u.getCreateDate());
            user.setId(id);
            result = usersList.replace(id, u, user);
        }
        return result;
    }

    @Override
    public boolean delete(UUID id) {
       return usersList.remove(id, findById(id));
    }

    @Override
    public ArrayList<User> findAll() {

        return new ArrayList<>(usersList.values());
    }

    @Override
    public User findById(UUID id) {
        return usersList.get(id);
    }

}
