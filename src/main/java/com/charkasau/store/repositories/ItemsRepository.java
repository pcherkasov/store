package com.charkasau.store.repositories;

import com.charkasau.store.models.Item;

import java.util.Map;

/**
 * Class ItemsRepository.
 * create 12.11.2018.
 *
 * @author Pavel Charkasau.
 */
public interface ItemsRepository {
    Map<String, Item> findAll();

    Item getItemById(int id);

    /**
     * Returns object Item from Storage by name.
     * @param name name of Item.
     * @return Item by name.
     */
    Item getItem(String name);

    /**
     * Saves new item in storage.
     * @param item new item for saving.
     */
    void save(Item item);
}
