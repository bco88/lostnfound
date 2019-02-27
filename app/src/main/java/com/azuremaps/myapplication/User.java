package com.azuremaps.myapplication;

import java.util.Date;

public class User {
    public User(){}

    private int id;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getLastSync() {
        return lastSync;
    }

    public void setLastSync(Date lastSync) {
        this.lastSync = lastSync;
    }

    private Location location;
    private Date lastSync;
}
