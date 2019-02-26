package com.azuremaps.myapplication.utils;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public final class Utility {
    public static void requestLocationPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[] {ACCESS_FINE_LOCATION}, 1);
    }

}
