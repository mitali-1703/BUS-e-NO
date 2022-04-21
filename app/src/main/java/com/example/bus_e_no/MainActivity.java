package com.example.bus_e_no;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.bus_e_no.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding b;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

//        To tell the Activity that the ActionBar coming on this Activity isn't the default Theme ActionBar but its the custom Toolbar
//        that we have made for this activity
        setSupportActionBar(b.appBar);

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        toggle = new ActionBarDrawerToggle(this,b.drawerLayout,R.string.open,R.string.close);
        toggle.syncState();


        b.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.editProfile:
                        Intent edit = new Intent(MainActivity.this,PersonalDetailsActivity.class);
                        startActivity(edit);
                        return true;

                    case R.id.about:
                        Intent about = new Intent(MainActivity.this,AboutActivity.class);
                        startActivity(about);
                        return true;

//                        case R.id.share:
//                        Intent share = new Intent(MainActivity.this,PersonalDetailsActivity.class);
//                        startActivity(share);
//                        return true;
//
//                    case R.id.feedback:
//                        Intent feed = new Intent(MainActivity.this,PersonalDetailsActivity.class);
//                        startActivity(feed);
//                        return true;
                }
                return true;
            }
        });
    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the navigation/hamburger icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.bellIcon:
                return true;
            case R.id.help:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //To have items to the right of Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}