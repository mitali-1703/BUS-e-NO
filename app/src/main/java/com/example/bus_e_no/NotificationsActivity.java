package com.example.bus_e_no;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bus_e_no.databinding.ActivityNotificationsBinding;

public class NotificationsActivity extends AppCompatActivity {
    ActivityNotificationsBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityNotificationsBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());


    }
}