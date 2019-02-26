package com.azuremaps.myapplication;

import java.util.List;
import java.util.Map;

public class MockStore implements IDataStore {
    @Override
    public Map<User, Distance> GetFinders(Location location) {
        return null;
    }

    @Override
    public Map<Item, Distance> GetItems(Location location) {
        return null;
    }

    @Override
    public long AddUser(User user) {
        return 0;
    }

    @Override
    public long AddItem(Item item) {
        return 0;
    }

    @Override
    public List<Item> GetUserItems(User user) {
        return null;
    }

    @Override
    public Item GetItem(long id) {
        return null;
    }
}
