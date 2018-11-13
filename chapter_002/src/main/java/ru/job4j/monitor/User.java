package ru.job4j.monitor;

public class User {
    private int id;
    private int amount;

    public User(int id, int amount) {
        this.setId(id);
        this.setAmount(amount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
