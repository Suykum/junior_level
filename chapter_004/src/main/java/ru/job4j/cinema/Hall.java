package ru.job4j.cinema;

public class Hall {
    private int id;
    private int row;
    private int column;
    private int price;
    private boolean reserved;

    public Hall(int id, int row, int column, int price, boolean reserved) {
        this.setId(id);
        this.setRow(row);
        this.setColumn(column);
        this.setPrice(price);
        this.setReserved(reserved);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
