package ru.job4j.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserMapTest {

    @Test
    public void whenTheSameUser() {
        User user1 = new User("Anna", 1980, 02, 03, 1);
        User user2 = new User("Anna", 1980, 02, 03, 1);
        Map<User,Object> userMap = new HashMap<>();
        userMap.put(user1, 1);
        userMap.put(user2, 2);
        Set<Map.Entry<User, Object>> set = userMap.entrySet();
        System.out.println("userMap: " + userMap);
        System.out.println();
        System.out.println("hashCode of user1: " + user1.hashCode());
        System.out.println("hashCode of user2: " + user2.hashCode());
        System.out.println();
        System.out.println("equals result: " + user1.equals(user2));
        for (Map.Entry<User, Object> s : set) {
            System.out.println("Name: " + s.getKey().getName() + ", birthday: " + s.getKey().getBirthday().getTime().toString() +
                    ", number of children: " + s.getKey().getChildren());
        }
    }

}