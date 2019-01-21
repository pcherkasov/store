package com.charkasau.store.repositories;

import com.charkasau.store.models.Item;
import com.charkasau.store.models.Order;
import com.charkasau.store.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepositoryJdbc implements OrdersRepository {

    //language=SQL
    private static final String SELECT_ORDERS_BY_USER_ID = "SELECT * FROM \"order\" WHERE \"user_id\" = ?";
    private static final String SELECT_ITEMS_BY_ORDER_ID = "SELECT * FROM \"order_item\" WHERE \"order_id\" = ?";
    private Connection connection;


    public OrdersRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }



    @Override
    public List<Order> getOrdersByUser(User user) {

        List<Order> orders = new ArrayList<>();
        ItemsRepositoryJdbc itemsRepository = new ItemsRepositoryJdbc(connection);

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ORDERS_BY_USER_ID);
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));

                PreparedStatement statement1 = connection.prepareStatement(SELECT_ITEMS_BY_ORDER_ID);
                statement1.setInt(1, order.getId());
                ResultSet resultSetOrderItem = statement1.executeQuery();

                while (resultSetOrderItem.next()) {
                    int itemId = resultSetOrderItem.getInt("item_id");
                    order.addItem(itemsRepository.getItemById(itemId));
                }

                orders.add(order);
            }

            return orders;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Order> getOrdersByUserId(int id) {
        User user = new User(id);
        return getOrdersByUser(user);
    }


    private int getLastOrderId() {
        int lastId = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"order\" ");
            while (resultSet.next()) {
                lastId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    @Override
    public void saveUsersOrder(User user) {
        Order order = user.getOrder();
        int newId = getLastOrderId() + 1;
        order.setId(newId);

        try {
            PreparedStatement orderStatement = connection.prepareStatement("INSERT INTO \"order\" (\"user_id\", \"total_price\") VALUES (?, ?)");
            orderStatement.setInt(1, user.getId());
            orderStatement.setInt(2, order.getCost().intValue());
            orderStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Item item : order.getItems()) {
            try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO \"order_item\" (\"order_id\", \"item_id\") VALUES (?, ?)");
                statement.setInt(1, order.getId());
                statement.setInt(2, item.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
