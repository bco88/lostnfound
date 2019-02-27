package com.azuremaps.myapplication;

public class Distance {

    public Distance (double distanceInMeters)
    {
        this.distanceInMeters = distanceInMeters;
    }

    public double getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(double distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }


    private double distanceInMeters;
}
