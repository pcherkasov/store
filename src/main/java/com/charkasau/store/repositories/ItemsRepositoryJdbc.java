package com.charkasau.store.repositories;

import com.charkasau.store.models.Item;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ItemsRepositoryJdbc implements ItemsRepository {

    private Connection connection;

    public ItemsRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Map<String, Item> findAll() {
        try {
            Map<String, Item> items = new HashMap<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"item\" ");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("title");
                BigDecimal price = BigDecimal.valueOf(resultSet.getInt("price"));

                Item item = new Item(id, name, price);

                items.put(item.getName(), item);
            }

            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }

        return null;
    }

    @Override
    public Item getItemById(int itemId) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"item\" WHERE \"id\" = ?");
            statement.setInt(1, itemId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                BigDecimal price = BigDecimal.valueOf(resultSet.getInt("price"));

                return new Item(id, title, price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }

        return null;
    }

    @Override
    public Item getItem(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"item\" WHERE \"title\" = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                BigDecimal price = BigDecimal.valueOf(resultSet.getInt("price"));

                return new Item(id, title, price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }

        return null;
    }

    @Override
    public void save(Item item) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO \"item\" (\"title\", \"price\") VALUES (?, ?)");
            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice().intValue());

        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
}
