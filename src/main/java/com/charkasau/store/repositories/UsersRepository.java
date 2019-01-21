package com.charkasau.store.repositories;

import com.charkasau.store.models.User;

import java.util.List;

public interface UsersRepository {
    boolean isExist(String name);
    void addUser(User user);
    User getUserByName(String name);
    User getUserById(int id);
    List<User> getAll();
}
