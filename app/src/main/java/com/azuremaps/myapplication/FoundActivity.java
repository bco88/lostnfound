package com.azuremaps.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class FoundActivity extends BaseActivity  {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextMessage = (TextView) findViewById(R.id.message);
        mTextMessage.setText("Found");
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_found;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_found;
    }

}
