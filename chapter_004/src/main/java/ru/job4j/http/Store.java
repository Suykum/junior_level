package ru.job4j.http;


import java.util.List;
import java.util.UUID;

public interface Store {

     boolean add(User user);

     boolean update(UUID id, User user);

     boolean delete(UUID id);

     List findAll();

     User findById(UUID id);
}
