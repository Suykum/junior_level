package ru.job4j.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserMapTest {

    @Test
    public void whenTheSameUser() {
        User user1 = new User("Anna", 28, 1);
        User user2 = new User("Anna", 28, 1);
        Map<User, Object> userMap = new HashMap<>();
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
            System.out.println("Name: " + s.getKey().getName() + ", birthday: " + s.getKey().getAge()
                    + ", number of children: " + s.getKey().getChildren());
        }
    }
   /* public static void main(String[] args) {

        User user1 = new User("Anna", 28,  1);
        User user2 = new User("Anna", 28,  1);
        int i = user1.hashCode();
        int i2 = user2.hashCode();
        System.out.println("First user's hashCode:  " + i);
        System.out.println("Second user's hashCode: " + i2);
        //далее на основе данного значения вычисляется хэш
        System.out.println("=======================================");
        int hash = hash(user1);
        int hash2 = hash(user2);
        System.out.println("First user's hash:  " + hash);
        System.out.println("Second user's hash: " + hash2);
        System.out.println("=======================================");
        //далее на основе хэша вычисляется ячейка в массиве будем считать дефолтный размер мапы = 16
        int index = index(hash);
        int index2 = index(hash2);
        System.out.println(index);
        System.out.println(index2);

    }
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    static int index(int hash) {
        return (16 - 1) & hash;
    }
*/

}