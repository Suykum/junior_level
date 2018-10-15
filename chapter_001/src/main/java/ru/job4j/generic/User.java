package ru.job4j.generic;

public class User extends Base {
    private String userName;
    protected User(String id, String name) {
        super(id);
        userName = name;
    }

    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }
}
