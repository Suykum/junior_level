package ru.job4j.servlet2;

import java.sql.Date;
import java.util.UUID;


public class User {
    private UUID id;
    private String name;
    private String login;
    private String email;
    private Date createDate;
    private String password;
    private Role.Roles role;

    public User(String name, String login, String email, String password, Role.Roles role) {
        this.setName(name);
        this.setLogin(login);
        this.setEmail(email);
        createDate = new Date(System.currentTimeMillis());
        this.setPassword(password);
        this.setRole(role);
    }

    public User(UUID id, String name, String login, String email, String password, Date date, Role.Roles role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = date;
        this.setPassword(password);
        this.setRole(role);

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role.Roles getRole() {
        return role;
    }

    public void setRole(Role.Roles role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        User user = (User) o;
        return this.id.equals(user.id) && this.name.equals(user.name) && this.login.equals(user.login) && this.email.equals(user.email);
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
