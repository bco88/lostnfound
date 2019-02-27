package com.azuremaps.myapplication;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import com.azuremaps.myapplication.utils.Utility;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.microsoft.azure.maps.mapcontrol.MapControl;
import com.microsoft.azure.maps.mapcontrol.layer.SymbolLayer;
import com.microsoft.azure.maps.mapcontrol.options.c;
import com.microsoft.azure.maps.mapcontrol.source.DataSource;

import java.util.ArrayList;
import java.util.List;

import static com.microsoft.azure.maps.mapcontrol.options.SymbolLayerOptions.iconImage;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class FindActivity extends BaseActivity  {

    MapControl mapControl;
    DataSource lostItemsDS;
    private FusedLocationProviderClient client;
    private double curLat, curLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = LocationServices.getFusedLocationProviderClient(this);
        mapControl = findViewById(R.id.findMap);
        mapControl.onCreate(savedInstanceState);

        lostItemsDS = new DataSource();
        List<Point> lostItems = new ArrayList<>();
        lostItems.add(Point.fromLngLat(-122.331, 47.64));
        lostItems.add(Point.fromLngLat(-112.33, 47.641));
        lostItems.add(Point.fromLngLat(-122.231, 37.521));
        lostItems.add(Point.fromLngLat(-119.331, 40.641));
        lostItems.add(Point.fromLngLat(-121.931, 37.541));
        lostItems.add(Point.fromLngLat(-121.931, 37.341));

        lostItemsDS.add(lostItems.toArray(new Point[0]));

        SymbolLayer lostItemsLayer = new SymbolLayer(lostItemsDS);
        lostItemsLayer.setOptions(iconImage("red-icon"));

        mapControl.getMapAsync(map -> {
            map.images.add("red-icon", R.drawable.mapcontrol_marker_red);
            map.sources.add(lostItemsDS);
            map.layers.add(lostItemsLayer);
        });

        Utility.requestLocationPermission(this);

        Button button = findViewById(R.id.getCurLocation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(FindActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                client.getLastLocation().addOnSuccessListener(FindActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null) {
                            Button button = findViewById(R.id.getCurLocation);

                            curLat = location.getLatitude();
                            curLon = location.getLongitude();

                            mapControl.getMapAsync(map -> {
                                DataSource dataSource = new DataSource();
                                dataSource.add(Feature.fromGeometry(Point.fromLngLat(curLon,  curLat)));

                                SymbolLayer symbolLayer = new SymbolLayer(dataSource);
                                symbolLayer.setOptions(iconImage("blue-icon"));

                                map.images.add("blue-icon", R.drawable.mapcontrol_marker_blue);
                                map.sources.add(dataSource);
                                map.layers.add(symbolLayer);
                                map.setCamera(new c(Point.fromLngLat(curLon, curLat), 0,0,9,1,20));
                            });
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapControl.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapControl.onPause();
    }
    @Override
    public void onStop() {
        super.onStop();
        mapControl.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapControl.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapControl.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapControl.onSaveInstanceState(outState);
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_find;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_find;
    }

}
