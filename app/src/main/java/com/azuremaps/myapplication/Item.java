package com.azuremaps.myapplication;

public class Item {
    public long getId() {
        return id;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item(User user) {
        this.user = user;
    }

    private User user;
    private long id;
    private boolean found;
    private String description;
}
