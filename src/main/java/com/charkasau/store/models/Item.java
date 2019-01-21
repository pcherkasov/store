package com.charkasau.store.models;

import java.math.BigDecimal;

/**
 * Class Item.
 * create 11.11.2018.
 *
 * @author Pavel Charkasau.
 */
public class Item {

    private int id;
    private String name;
    private BigDecimal price;

    public Item(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
        price.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
