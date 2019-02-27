package com.azuremaps.myapplication;

public class DataStore {
    private static final IDataStore ourInstance = new MockStore();

    public static IDataStore getInstance() {
        return ourInstance;
    }
}
