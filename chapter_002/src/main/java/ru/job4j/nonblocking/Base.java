package ru.job4j.nonblocking;

public class Base {
    private int id;
    private int version;

    public Base(int id, int version) {
        this.setId(id);
        this.setVersion(version);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
