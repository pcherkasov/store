package com.charkasau.store.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Order.
 * create 11.11.2018.
 *
 * @author Pavel Charkasau.
 */
public class Order {

    private int id;
    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public BigDecimal getCost() {
        BigDecimal cost = new BigDecimal("0");

        for (Item sum : items) {
            cost = cost.add(sum.getPrice());
        }
        return cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
