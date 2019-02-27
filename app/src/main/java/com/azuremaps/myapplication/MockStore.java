package com.azuremaps.myapplication;

import com.mapbox.geojson.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockStore implements IDataStore {
    @Override
    public Map<User, Distance> GetFinders(Location location) {
        Map<User, Distance> result = new HashMap<User, Distance>();

        User u1 = new User();
        u1.setLocation(new Location(-122.331, 47.64));

        User u2 = new User();
        u2.setLocation(new Location(-122.33, 47.641));

        User u3 = new User();
        u3.setLocation(new Location(-122.331, 47.641));

        result.put(u1, new Distance(0.0));
        result.put(u2, new Distance(0.0));
        result.put(u3, new Distance(0.0));

        return result;
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
