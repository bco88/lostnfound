package com.azuremaps.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.microsoft.azure.maps.mapcontrol.AzureMap;
import com.microsoft.azure.maps.mapcontrol.AzureMaps;
import com.microsoft.azure.maps.mapcontrol.MapControl;
import com.microsoft.azure.maps.mapcontrol.events.OnClick;
import com.microsoft.azure.maps.mapcontrol.events.OnReady;
import com.microsoft.azure.maps.mapcontrol.layer.SymbolLayer;
import com.microsoft.azure.maps.mapcontrol.options.LineLayerOptions;
import com.microsoft.azure.maps.mapcontrol.options.MapStyle;
import com.microsoft.azure.maps.mapcontrol.options.Option;
import com.microsoft.azure.maps.mapcontrol.source.DataSource;
import com.microsoft.azure.maps.mapcontrol.layer.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import static com.microsoft.azure.maps.mapcontrol.options.SymbolLayerOptions.iconImage;


public class LostActivity extends BaseActivity {

    MapControl mapControl;

    private TextView mTextMessage;


    DataSource dataSource;
    DataSource lineSource;
    DataSource clickSource;
    Point clickedLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mTextMessage = (TextView) findViewById(R.id.message);
        mapControl = findViewById(R.id.mapcontrol);
        mapControl.onCreate(savedInstanceState);

        mapControl.getMapAsync((map) ->
        {
            // Clicked source and layer
            clickSource = new DataSource();

            SymbolLayer clickLayer = new SymbolLayer(clickSource);
            clickLayer.setOptions(iconImage("blue-icon"));

            map.images.add("blue-icon", R.drawable.mapcontrol_marker_blue);
            map.sources.add(clickSource);
            map.layers.add(clickLayer);


            //Other stuff
            dataSource = new DataSource();
            lineSource = new DataSource();
            SymbolLayer symbolLayer = new SymbolLayer(dataSource);
            symbolLayer.setOptions(iconImage("my-icon"));
            //dataSource.add(Point.fromLngLat(-122.33, 47.64));

            map.images.add("my-icon", R.drawable.mapcontrol_marker_red);
            map.sources.add(dataSource);
            map.layers.add(symbolLayer);

//            LineLayer lineLayer = new LineLayer(lineSource, LineLayerOptions.strokeColor("black"), LineLayerOptions.strokeWidth(5));
//            map.sources.add(lineSource);
//            map.layers.add(lineLayer);

            map.events.add((OnClick) (lat, lon) -> {

                clickSource.clear();
                clickSource.add(Feature.fromGeometry(Point.fromLngLat(lon,  lat)));

//                clickedLocation = Point.fromLngLat(lon, lat);
//                dataSource.clear();
//                dataSource.add(clickedLocation);
            });

        });

        findViewById(R.id.button_send).setOnClickListener(v -> {

            Map<User, Distance> finders = DataStore.getInstance().GetFinders(clickedLocation);
            if (dataSource != null) {
                for( Map.Entry<User, Distance> entry : finders.entrySet())
                {
                    dataSource.add(entry.getKey().getLocation());
                }
            }

            drawLinesToItem(clickedLocation);
            CreateNewItem();
        });
    }

    private void drawLinesToItem(Point item)
    {
        Map<User, Distance> finders = DataStore.getInstance().GetFinders(item);

        if (lineSource != null) {
            for( Map.Entry<User, Distance> entry : finders.entrySet())
            {
                ArrayList<Point> points = new ArrayList<>();
                points.add(item);
                points.add( entry.getKey().getLocation());
                lineSource.add(LineString.fromLngLats(points));
            }
        }


    }

    private void CreateNewItem()
    {

        //alert stuff

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Submit Lost Item");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText latTextBox = new EditText(this);
        latTextBox.setHint("Latitude");
        layout.addView(latTextBox);



        final EditText lonTextBox = new EditText(this);
        lonTextBox.setHint("Longitude");
        layout.addView(lonTextBox);

        latTextBox.setText(String.valueOf(clickedLocation.latitude()));
        lonTextBox.setText(String.valueOf(clickedLocation.longitude()));


        final EditText descriptionBox = new EditText(this);
        descriptionBox.setHint("Description");
        layout.addView(descriptionBox);

        builder.setView(layout);

// Set up the buttons
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String lat = latTextBox.getText().toString();
                String lon = lonTextBox.getText().toString();
                String desc = descriptionBox.getText().toString();

                Item item = new Item();
                item.setLocation(Point.fromLngLat(Double.valueOf(lon), Double.valueOf(lat)));
                item.setUser(new User("joe"));
                DataStore.getInstance().AddItem(item);
            }

        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
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
