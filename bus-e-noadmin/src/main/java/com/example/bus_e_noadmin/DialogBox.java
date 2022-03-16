package com.example.bus_e_noadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bus_e_noadmin.databinding.ActivityDialogBoxBinding;

public class DialogBox extends AppCompatActivity {

    ActivityDialogBoxBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityDialogBoxBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());


    }
}