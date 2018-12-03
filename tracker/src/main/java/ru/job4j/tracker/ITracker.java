package ru.job4j.tracker;

import java.util.ArrayList;

public interface ITracker {
    Item add(Item item);
    boolean replace(String id, Item item);
    boolean delete(String id);
    ArrayList<Item> getAll();
    ArrayList<Item> findByName(String key);
    Item findById(String id);
}
