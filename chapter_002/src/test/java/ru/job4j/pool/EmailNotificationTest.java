package ru.job4j.pool;

import java.util.ArrayList;
import java.util.List;

public class EmailNotificationTest {
    public static void main(String[] args) {
        EmailNotification notification = new EmailNotification();
        List<User> users = new ArrayList<>();
        users.add(new User("Anna", "anna@gmail.com"));
        users.add(new User("Sasha", "sasha@gmail.com"));
        users.add(new User("Masha", "masha@gmail.com"));
        users.add(new User("Misha", "misha@gmail.com"));

        for (User u : users) {
            notification.emailTo(u);
        }

        notification.close();
    }


}