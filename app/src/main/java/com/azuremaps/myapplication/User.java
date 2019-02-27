package com.azuremaps.myapplication;

import com.mapbox.geojson.Point;

import java.util.Date;

public class User {
    public User(){}

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private int id;

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Date getLastSync() {
        return lastSync;
    }

    public void setLastSync(Date lastSync) {
        this.lastSync = lastSync;
    }

    public User(String name) {
        this.name = name;
    }

    private String name;
    private Point location;
    private Date lastSync;
}
