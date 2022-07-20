package com.example.bus_e_no;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.bus_e_no.databinding.ActivityManageOtpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

public class ManageOtp extends AppCompatActivity {

    ActivityManageOtpBinding b;
    String phoneNumber;
    String otpid;
    private FirebaseAuth mAuth;
    Boolean otpVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityManageOtpBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

//        otpVerify = false;
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        phoneNumber = getIntent().getStringExtra("mobile").toString();

        b.tvResendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ManageOtp.this, "OTP Sent Successfully.", Toast.LENGTH_SHORT).show();
            }
        });


        initiateOtp();

        editTextInput();

        b.vfyOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.progressBarVerify.setVisibility(View.VISIBLE);
                b.vfyOtpBtn.setVisibility(View.INVISIBLE);
                if (b.etC1.getText().toString().trim().isEmpty() ||
                        b.etC2.getText().toString().trim().isEmpty() ||
                        b.etC3.getText().toString().trim().isEmpty() ||
                        b.etC4.getText().toString().trim().isEmpty() ||
                        b.etC5.getText().toString().trim().isEmpty() ||
                        b.etC6.getText().toString().trim().isEmpty()) {
                    b.progressBarVerify.setVisibility(View.INVISIBLE);
                    b.vfyOtpBtn.setVisibility(View.VISIBLE);
                    Toast.makeText(ManageOtp.this, "OTP is not Valid!", Toast.LENGTH_SHORT).show();
                } else {
                    if (phoneNumber != null) {
                        String code = b.etC1.getText().toString().trim() +
                                b.etC2.getText().toString().trim() +
                                b.etC3.getText().toString().trim() +
                                b.etC4.getText().toString().trim() +
                                b.etC5.getText().toString().trim() +
                                b.etC6.getText().toString().trim();

                        //To check that OTP entered by the user matches the one sent by Firebase,
                        // we are creating an PhoneAuthCredential object.
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpid, code);
                        signInWithPhoneAuthCredential(credential);

                    }
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
                        })         // OnVerificationStateChangedCallbacks
                        .build();
                PhoneAuthProvider.verifyPhoneNumber(options);
            }


            private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
                mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            otpVerify = true;
                            SharedPreferences prefs = getSharedPreferences("verified",MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("verify", String.valueOf(otpVerify));
                            editor.putBoolean("isVerified",true);
                            editor.apply();

                            b.progressBarVerify.setVisibility(View.VISIBLE);
                            b.vfyOtpBtn.setVisibility(View.INVISIBLE);

                            /** Checking if the user that is trying to log in is a registered user or not by
                             * getting user's phone number's unique id
                             */
                            FirebaseUser user = task.getResult().getUser();
                            String uid = user.getUid();
                            FirebaseFirestore.getInstance().collection("Users").document(phoneNumber).get()
                                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                @Override
                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                    if(documentSnapshot.exists()){
//                                                        b.progressBarVerify.setVisibility(View.VISIBLE);
//                                                        b.vfyOtpBtn.setVisibility(View.INVISIBLE);
                                                        startActivity(new Intent(ManageOtp.this, HomeActivity.class));
                                                        Toast.makeText(ManageOtp.this, "Welcome...", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        Intent intent = new Intent(ManageOtp.this, RegistrationActivity.class);
                                                        intent.putExtra("phone", phoneNumber);
                                                        startActivity(intent);
                                                    }
                                                    finish();
                                                }
                                            });

//                            b.progressBarVerify.setVisibility(View.VISIBLE);
//                            b.vfyOtpBtn.setVisibility(View.INVISIBLE);
//                            startActivity(new Intent(ManageOtp.this, HomeActivity.class));
//                            Toast.makeText(ManageOtp.this, "Welcome...", Toast.LENGTH_SHORT).show();
//                            finish();

//                            DocumentReference docIdRef = FirebaseFirestore.getInstance().collection("Users").document(phoneNumber);
//                            docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                    if (task.isSuccessful()) {
//                                        DocumentSnapshot document = task.getResult();
//                                        if (document.exists()) {
//                                            startActivity(new Intent(ManageOtp.this, HomeActivity.class));
//                                            Toast.makeText(ManageOtp.this, "Welcome...", Toast.LENGTH_SHORT).show();
//                                            finish();
//                                        }
//                                    } else {
//                                        startActivity(new Intent(ManageOtp.this, RegistrationActivity.class));
//                                        finish();
//                                    }
//                                }
//                            });


                        } else {
                            b.progressBarVerify.setVisibility(View.GONE);
                            b.vfyOtpBtn.setVisibility(View.VISIBLE);
                            Toast.makeText(ManageOtp.this, "Signin Code Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


    private void editTextInput() {
        b.etC1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                b.etC2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b.etC2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                b.etC3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b.etC3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                b.etC4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b.etC4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                b.etC5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b.etC5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                b.etC6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}