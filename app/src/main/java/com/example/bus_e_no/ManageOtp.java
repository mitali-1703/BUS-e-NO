package com.example.bus_e_no;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.bus_e_no.databinding.ActivityManageOtpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ManageOtp extends AppCompatActivity {

    ActivityManageOtpBinding b;
    String phoneNumber;
    String otpid;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityManageOtpBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        phoneNumber = getIntent().getStringExtra("mobile").toString();

        initiateOtp();

        b.vfyOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b.enterOtpEdt.getText().toString().isEmpty())
                    Toast.makeText(ManageOtp.this, "Blank field can't be processed", Toast.LENGTH_SHORT).show();
                else if(b.enterOtpEdt.getText().toString().length()!=6)
                    Toast.makeText(ManageOtp.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                else{
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpid,b.enterOtpEdt.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });
    }

    // To send Verification code(OTP) to user's phone
    private void initiateOtp() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            //When sim isn't in the same device
                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                otpid = s;
                            }

                            //When sim is in the same device
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            //When OTP verification failed
                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(ManageOtp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(ManageOtp.this,MainActivity.class));
                            finish();
                        } 
                        else {
                            Toast.makeText(ManageOtp.this, "Signin Code Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}