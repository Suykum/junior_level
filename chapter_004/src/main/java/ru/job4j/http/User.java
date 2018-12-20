package ru.job4j.http;

import java.sql.Date;


public class User {
    private String id;
    private String name;
    private String login;
    private String email;
    private Date createDate;

    public User(String id, String name, String login, String email) {
        this.setId(id);
        this.setName(name);
        this.setLogin(login);
        this.setEmail(email);
        createDate = new Date(System.currentTimeMillis());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date date) {
        createDate = date;
    }

    @Override
    public boolean equals(Object o) {
        User user = (User) o;
        return this.id == user.id && this.name.equals(user.name) && this.login.equals(user.login) && this.email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return id.hashCode() + name.hashCode() + login.hashCode() + email.hashCode();
    }

    @Override
    public String toString() {
        return "User id: " + this.id + ", Name: " + name + ", Login: " + this.login
                + ", Email: " + this.email + ", Created date: " + this.createDate;
    }
}
