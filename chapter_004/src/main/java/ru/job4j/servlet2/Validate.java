package ru.job4j.servlet2;

import java.util.List;
import java.util.UUID;

public interface Validate {
    String add(User user);
    String update(UUID id, User user);
    String delete(UUID id);
    List findAll();
    User findById(UUID id);
}
