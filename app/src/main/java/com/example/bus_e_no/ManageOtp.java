package com.example.bus_e_no;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bus_e_no.databinding.ActivityManageOtpBinding;

public class ManageOtp extends AppCompatActivity {

    ActivityManageOtpBinding b;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityManageOtpBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        phoneNumber = getIntent().getStringExtra("mobile").toString();
    }
}