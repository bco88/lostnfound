package com.azuremaps.myapplication;

import com.mapbox.geojson.Point;

import java.util.List;
import java.util.Map;

public interface IDataStore {
    Map<User, Distance> GetFinders(Point location);
    Map<Item, Distance> GetItems(Point location);
    long AddUser(User user);
    long AddItem(Item item);
    List<Item> GetUserItems(User user);
    Item GetItem(long id);
}
