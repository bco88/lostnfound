package com.azuremaps.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.microsoft.azure.maps.mapcontrol.AzureMap;
import com.microsoft.azure.maps.mapcontrol.AzureMaps;
import com.microsoft.azure.maps.mapcontrol.MapControl;
import com.microsoft.azure.maps.mapcontrol.events.OnClick;
import com.microsoft.azure.maps.mapcontrol.events.OnReady;
import com.microsoft.azure.maps.mapcontrol.layer.SymbolLayer;
import com.microsoft.azure.maps.mapcontrol.options.MapStyle;
import com.microsoft.azure.maps.mapcontrol.source.DataSource;
import com.microsoft.azure.maps.mapcontrol.layer.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import static com.microsoft.azure.maps.mapcontrol.options.SymbolLayerOptions.iconImage;


public class LostActivity extends BaseActivity {

    MapControl mapControl;

    private TextView mTextMessage;

    /** Called when the user touches the button */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sendMessage(View view) {
        List<Point> points = new ArrayList<>();
        points.add(Point.fromLngLat(-122.331, 47.64));
        points.add(Point.fromLngLat(-122.33, 47.641));
        points.add(Point.fromLngLat(-122.331, 47.641));

        if (dataSource != null) {
            dataSource.add(points.toArray(new Point[0]));
        }
    }

    DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mTextMessage = (TextView) findViewById(R.id.message);
        mapControl = findViewById(R.id.mapcontrol);
        mapControl.onCreate(savedInstanceState);

        mapControl.getMapAsync((map) ->
        {
            dataSource = new DataSource();
            SymbolLayer symbolLayer = new SymbolLayer(dataSource);
            symbolLayer.setOptions(iconImage("my-icon"));
            dataSource.add(Feature.fromGeometry(Point.fromLngLat(-122.33, 47.64)));

            map.images.add("my-icon", R.drawable.mapcontrol_marker_red);
            map.sources.add(dataSource);
            map.layers.add(symbolLayer);

            map.events.add((OnClick) (lat, lon) -> {
                dataSource.clear();
                dataSource.add(Feature.fromGeometry(Point.fromLngLat(lon, lat)));
            });
        });

        findViewById(R.id.button_send).setOnClickListener(v -> {

            
            List<Point> points = new ArrayList<>();
            points.add(Point.fromLngLat(-122.331, 47.64));
            points.add(Point.fromLngLat(-122.33, 47.641));
            points.add(Point.fromLngLat(-122.331, 47.641));

            if (dataSource != null) {
                dataSource.add(points.toArray(new Point[0]));
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
    int getContentViewId() {
        return R.layout.activity_lost;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_lost;
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
}
