package ru.job4j.map;

import java.util.Calendar;

public class User {
    private String name;
    private Calendar birthday = Calendar.getInstance();
    private int children;

    public User(String name,  int birthYear, int birthMonth, int birthDate, int children) {
        this.setName(name);
        this.birthday.set(birthYear, birthMonth, birthDate);
        this.setChildren(children);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    /*@Override
    public boolean equals(Object object) {
        User user = (User) object;
        return this.name.equals(user.name) && this.birthday.equals(user.birthday)&& this.children == user.children;
    }*/

    @Override
    public int hashCode() {
        return this.name.hashCode() +  this.birthday.hashCode() + children;
    }
}
