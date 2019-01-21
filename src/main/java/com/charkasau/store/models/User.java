package com.charkasau.store.models;

/**
 * Class User.
 * create 11.11.2018.
 *
 * @author Pavel Charkasau.
 */
public class User {

    private int id;
    private String name;
    private String password;
    private Order order = new Order();

    public User(int id){
        this.id = id;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
