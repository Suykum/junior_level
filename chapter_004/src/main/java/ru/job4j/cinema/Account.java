package ru.job4j.cinema;

public class Account {
    private int id;
    private String name;
    private String telNumber;
    private int seatID;

    public Account(String name, String telNumber) {
        this.setName(name);
        this.setTelNumber(telNumber);
    }

    public Account(int id, String name, String telNumber, int seatID) {
        this.setId(id);
        this.setName(name);
        this.setTelNumber(telNumber);
        this.setSeatID(seatID);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }
}
