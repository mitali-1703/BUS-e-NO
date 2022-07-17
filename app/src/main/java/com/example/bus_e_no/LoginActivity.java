package com.example.bus_e_no;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
                if(b.enterNumEdt.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                }
                else if(b.enterNumEdt.getText().toString().length()!=10){
                    Toast.makeText(LoginActivity.this, "Type valid Phone Number", Toast.LENGTH_SHORT).show();
                }
                else {
                    b.progressBar.setVisibility(View.VISIBLE);
                    b.getOtpBtn.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "OTP successfully sent", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, ManageOtp.class);
                    intent.putExtra("mobile", b.ccp.getFullNumberWithPlus().replace(" ", ""));
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}