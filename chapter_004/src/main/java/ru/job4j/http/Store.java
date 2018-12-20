package ru.job4j.http;


import java.util.List;

public interface Store {

     boolean add(User user);

     boolean update(String id, User user);

     boolean delete(String id);

     List findAll();

     User findById(String id);
}
