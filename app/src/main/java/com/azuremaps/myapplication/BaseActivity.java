package com.azuremaps.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    protected BottomNavigationView navigationView;

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        navigationView.postDelayed(() -> {
        switch (item.getItemId()) {
            case R.id.navigation_lost:
                startActivity(new Intent(this, LostActivity.class));
                break;
            case R.id.navigation_found:
                startActivity(new Intent(this, FoundActivity.class));
                break;
            case R.id.navigation_find:
                startActivity(new Intent(this, FindActivity.class));
                break;
        }
        finish();
        }, 300);
        return true;
    }

    private void updateNavigationBarState(){
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    void selectBottomNavigationBarItem(int itemId) {
        Menu menu = navigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            boolean shouldBeChecked = item.getItemId() == itemId;
            if (shouldBeChecked) {
                item.setChecked(true);
                break;
            }
        }
    }

    abstract int getContentViewId();

    abstract int getNavigationMenuItemId();



}
