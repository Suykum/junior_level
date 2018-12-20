package ru.job4j.http;

import java.util.List;

public class ValidateUser {
    private static ValidateUser validateUserObject = new ValidateUser();

    private ValidateUser() {
    }

    public static ValidateUser getValidateUserObject() {
        return validateUserObject;
    }
    private final Store memoryStore = MemoryStore.getMemoryStoreObject();



    public String add(User user) {
       boolean result = memoryStore.add(user);
       return result ? "New user is added" : "User with id " + user.getId() + " already exist";
    }


    public String update(String id, User user) {
        boolean updateResult = memoryStore.update(id, user);
        return updateResult ? "User is updated" : "User with id " + id + " not exist";
    }


    public String delete(String id) {
        boolean deleteResult = memoryStore.delete(id);
        return deleteResult ? "User deleted" : "User with id " + id + " not exist";
    }


    public String findAll() {
        String findAllResult;
        List list = memoryStore.findAll();
        if (list.size() != 0) {
            findAllResult = list.toString();
        } else {
            findAllResult = "User storage is empty";
        }
        return findAllResult;
    }


    public String findById(String id) {
        String findByIdResult;
        User u = memoryStore.findById(id);
        if (u != null) {
            findByIdResult = u.toString();
        } else {
            findByIdResult = "User with id " + id + " not exist";
        }
        return findByIdResult;
    }
}
