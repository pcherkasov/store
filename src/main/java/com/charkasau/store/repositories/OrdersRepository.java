package com.charkasau.store.repositories;

import com.charkasau.store.models.Order;
import com.charkasau.store.models.User;

import java.util.List;

public interface OrdersRepository {
    List<Order> getOrdersByUser(User user);
    List<Order> getOrdersByUserId(int id);
    void saveUsersOrder(User user);
}
