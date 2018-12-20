package ru.job4j.http;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUser {
    private static ValidateUser validateUserObject = new ValidateUser();
    private static final Logger LOGGER = Logger.getLogger(ValidateUser.class);
    private ValidateUser() {
    }

    public static ValidateUser getValidateUserObject() {
        return validateUserObject;
    }
    private final Store memoryStore = MemoryStore.getMemoryStoreObject();


    public String add(User user) {

        boolean result = false;
        try {
            if (user.getLogin() != null && user.getEmail() != null && !isLoginExist(user.getLogin()) && !isEmailExist(user.getEmail()) && emailValidation(user.getEmail())) {
                result = memoryStore.add(user);
            }
        } catch (UserExeption e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result ? "New user added" : "Cannot be added";
    }


    public String update(UUID id, User user) {
        boolean updateResult = false;
        try {
            if (user.getLogin() != null && user.getEmail() != null && !isLoginExist(user.getLogin()) && !isEmailExist(user.getEmail()) && emailValidation(user.getEmail())) {
                updateResult = memoryStore.update(id, user);
            }
        } catch (UserExeption e) {
            LOGGER.error(e.getMessage(), e);
        }
        return updateResult ? "User is updated" : "User with id " + id + " not exist";
    }

    public String delete(UUID id) {
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


    public String findById(UUID id) {
        String findByIdResult;
        User u = memoryStore.findById(id);
        if (u != null) {
            findByIdResult = u.toString();
        } else {
            findByIdResult = "User with id " + id + " not exist";
        }
        return findByIdResult;
    }

    private boolean emailValidation(String email) throws UserExeption {
        String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        boolean r = true;
        if (!matcher.matches()) {
            throw new UserExeption("Enter valid email address");
        }
        return r;
    }

    private boolean isLoginExist(String login) throws UserExeption {
        boolean r = false;
        List<User> list = memoryStore.findAll();
        for (User u : list) {
            if (u.getLogin().equals(login)) {
                throw new UserExeption("User with " + login + " login already exist");
            }
        }
        return r ;
    }

    private boolean isEmailExist(String email) throws UserExeption {
        boolean r = false;
        List<User> list = memoryStore.findAll();
        for (User u : list) {
            if (u.getEmail().equals(email)) {
                throw new UserExeption("User with " + email + " email already exist");
            }
        }
        return r ;
    }
}
