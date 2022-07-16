package com.example.bus_e_no;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bus_e_no.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.ccp.registerCarrierNumberEditText(b.enterNumEdt);

        b.getOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ManageOtp.class);
                intent.putExtra("mobile",b.ccp.getFullNumberWithPlus().replace(" ",""));
                startActivity(intent);
            }
        });
    }
}