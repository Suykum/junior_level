package ru.job4j.map;

import java.util.Calendar;

public class User {
    private String name;
    //private Calendar birthday = Calendar.getInstance();
    private int children;
    private int age;

    public User(String name,  int age, int children) {
        this.setName(name);
        this.setAge(age);
        this.setChildren(children);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object object) {
        User user = (User) object;
        return this.name.equals(user.name) && this.age == user.age && this.children == user.children;
    }

    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + children;
        return result;
    }
}
