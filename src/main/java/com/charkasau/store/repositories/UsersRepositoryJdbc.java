package com.charkasau.store.repositories;

import com.charkasau.store.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbc implements UsersRepository {

    private Connection connection;

    public UsersRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"user\" ");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                User user = new User(id, name, password);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isExist(String name) {

        for (User user1 : getAll()) {
            if (user1.getName() == name) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void addUser(User user) {

        try {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO \"user\" (\"name\", \"password\") VALUES (?, ?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }

    }

    @Override
    public User getUserByName(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"user\" WHERE \"name\" = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("name");
                String userPassword = resultSet.getString("password");

                return new User(userId, userName, userPassword);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"user\" WHERE \"id\" = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("name");
                String userPassword = resultSet.getString("password");

                return new User(userId, userName, userPassword);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }

        return null;
    }

    public int getLastUserId() {
        int lastId = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"user\" ");
            while (resultSet.next()) {
                lastId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;
    }
}
