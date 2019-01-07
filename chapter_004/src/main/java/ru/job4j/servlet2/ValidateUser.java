package ru.job4j.servlet2;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUser implements Validate{
    private static final Logger LOGGER = Logger.getLogger(ValidateUser.class);

    private static Validate validateUserObject = new ValidateUser();
    private ValidateUser() {
    }
    public static Validate getValidateUserObject() {
        return validateUserObject;
    }

    //private final Store store = MemoryStore.getMemoryStoreObject();
    private final Store store = DbStore.getInstance();

    public String add(User user) {

        boolean result = false;
        try {
            if (user.getLogin() != null && user.getEmail() != null && !isLoginEmailExist(user.getLogin(), user.getEmail()) && emailValidation(user.getEmail())) {
                result = store.add(user);
            }
        } catch (UserExeption e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result ? "New user added" : "Cannot be added";
    }


    public String update(UUID id, User user) {
        boolean updateResult = false;

        try {
            if (user.getLogin() != null && user.getEmail() != null
                    && !isLoginEmailExistForUpdate(id, user.getLogin(), user.getEmail()) && emailValidation(user.getEmail())) {
                updateResult = store.update(id, user);
            }
        } catch (UserExeption e) {
            LOGGER.error(e.getMessage(), e);
        }
        return updateResult ? "User is updated" : "User cannot be updated";
    }

    public String delete(UUID id) {
        boolean deleteResult = store.delete(id);
        return deleteResult ? "User deleted" : "User with id " + id + " not exist";
    }


    public List findAll() {
        return store.findAll();
    }


    public User findById(UUID id) {
        User u = store.findById(id);
        return u;
    }

    private boolean emailValidation(String email) throws UserExeption {
        String emailRegex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        boolean r = true;
        if (!matcher.matches()) {
            throw new UserExeption("Enter valid email address");
        }
        return r;
    }

    private boolean isLoginEmailExist(String login, String email) throws UserExeption {
        boolean r = false;
        List<User> list = store.findAll();
        checkList(list, login, email);
        return r;
    }


    private boolean isLoginEmailExistForUpdate(UUID id, String login, String email) throws UserExeption {
        boolean r = false;
        User user = store.findById(id);
        if (user != null) {
            List<User> list = store.findAll();
            list.remove(user);
            checkList(list, login, email);
        }

        return r;
    }

    private void checkList(List<User> list, String login, String email) throws UserExeption {
        for (User u : list) {
            if (u.getLogin().equals(login)) {
                throw new UserExeption("User with " + login + " login already exist");
            }
            if (u.getEmail().equals(email)) {
                throw new UserExeption("User with " + email + " email already exist");
            }
        }
    }

    public boolean loginPermit(String login, String password) {
        boolean exists = false;
        List<User> users = findAll();
        for (User u : users) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public Role.Roles getRole(String login) {
        Role.Roles role = null;
        List<User> users = findAll();
        for (User u : users) {
            if (u.getLogin().equals(login)) {
                role = u.getRole();
            }
        }
        return role;
    }
}
