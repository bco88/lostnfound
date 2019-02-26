package com.azuremaps.myapplication;

import java.util.List;
import java.util.Map;

public interface IDataStore {
    Map<User, Distance> GetFinders(Location location);
    Map<Item, Distance> GetItems(Location location);
    long AddUser(User user);
    long AddItem(Item item);
    List<Item> GetUserItems(User user);
    Item GetItem(long id);
}
