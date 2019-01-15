package ru.job4j.servlet2;



import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ValidateStub implements Validate {

    private Map<UUID, User> store = new HashMap<>();
    @Override
    public String add(User user) {
        UUID id = UUID.randomUUID();
        user.setId(id);
        User u = store.putIfAbsent(id, user);
        return u == null ? "New user added" : "Cannot be added";
    }

    @Override
    public String update(UUID id, User user) {
        boolean result = false;
        User u = findById(id);
        if (u != null) {
            user.setCreateDate(u.getCreateDate());
            user.setId(id);
            result = store.replace(id, u, user);
        }
        return result ? "User is updated" : "User cannot be updated";
    }

    @Override
    public String delete(UUID id) {
       boolean deleteResult = store.remove(id, findById(id));
       return deleteResult ? "User deleted" : "User with id " + id + " not exist";
    }

    @Override
    public List findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public User findById(UUID id) {
        return store.get(id);
    }

    @Override
    public boolean loginPermit(String login, String password) {
        return false;
    }

    @Override
    public Role.Roles getRole(String login) {
        return null;
    }

    @Override
    public List<String> getCountries() {
        return null;
    }

    @Override
    public List<String> getCities(String country) {
        return null;
    }

}
