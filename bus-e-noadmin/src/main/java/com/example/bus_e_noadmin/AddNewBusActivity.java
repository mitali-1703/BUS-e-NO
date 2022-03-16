package com.example.bus_e_noadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;

public class AddNewBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_bus);

        MaterialToolbar materialToolbar = findViewById(R.id.appBar);
//        To tell the Activity that the ActionBar coming on this Activity isn't the default Theme ActionBar but its the custom Toolbar
//        that we have made for this activity
        setSupportActionBar(materialToolbar);

    }
}